package com.alan.microservices.event.handler.service;


import com.alan.microservices.commons.Result;
import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.service.DevFeignSvc;
import com.alan.microservices.event.handler.model.OnlineEvent;
import com.alan.microservices.event.handler.model.OnlineSummary;
import com.alan.microservices.event.handler.serde.StreamsSerdes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class DeviceOnlineService {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceOnlineService.class);
    public static final String ONLINE_STORE = "online";
    public static final String ONLINE_AMOUNT_STORE = "online-amount";
    public static final String INPUT_TOPIC = "site-status";
    public static final String ONLINE_TOPIC = "eh-online";
    public static final String ONLINE_BY_SITE_TOPIC = "eh-online-by-site";
    private KafkaStreams kafkaStreams;
    @Autowired
    private DevFeignSvc devFeignSvc;
    private ReadOnlyKeyValueStore<Long, OnlineSummary> onlineStore;
    private ReadOnlyKeyValueStore<Long, Long> onlineAmountStore;
    @PostConstruct
    public void start(){
        createStreams();
        kafkaStreams.start();
        while (kafkaStreams.state() != KafkaStreams.State.RUNNING) ;
        onlineStore = kafkaStreams.store(ONLINE_STORE, QueryableStoreTypes.keyValueStore());
        onlineAmountStore = kafkaStreams.store(ONLINE_AMOUNT_STORE, QueryableStoreTypes.keyValueStore());

    }
    public void createStreams() {
        Properties streamsConfig = getProperties();
        StreamsBuilder builder = new StreamsBuilder();

        Serde<OnlineEvent> onlineEventSerde = StreamsSerdes.OnlineEventSerde();
        Serde<OnlineSummary> onlineSummarySerde = StreamsSerdes.OnlineSummarySerde();
        Serde<Long> longSerde = Serdes.Long();
        //原始键值对类型 int, onlineEvent 时间戳可作为事件时间？
        KStream<String, OnlineEvent> source = builder.stream(INPUT_TOPIC, Consumed.with(null,onlineEventSerde));
        // map 操作 入参
        KeyValueMapper<String, OnlineEvent, KeyValue<Long, OnlineSummary>> mapper = (k, v) -> {
            Result<Device> result = devFeignSvc.getByToken(v.getToken());
            if (result.getCode() != 0) throw new RuntimeException("devFeignSvc 调用失败");
            Device device = result.getData();
            if (device == null) throw new RuntimeException(String.format("设备{token=%s} 不存在", k));
            return KeyValue.pair(device.getId(), OnlineSummary
                    .newBuilder(v).withDevId(device.getId())
                            .withSiteId(device.getSiteId())
                            .build());
        };

        KStream<Long, OnlineSummary> map = source.map(mapper);
        map.print(Printed.<Long, OnlineSummary>toSysOut().withLabel("map"));
        KStream<Long, OnlineSummary> repartition=map.through(ONLINE_TOPIC, Produced.with(longSerde, onlineSummarySerde));
        KTable<Long, OnlineSummary> onlineTable = repartition.groupByKey(Grouped.with(longSerde, onlineSummarySerde))
                .reduce((aggValue, newValue) -> newValue, Materialized.as(ONLINE_STORE));
        onlineTable.toStream().print(Printed.<Long, OnlineSummary>toSysOut().withLabel("online-table"));
        KTable<Long, Long> amountTable = onlineTable.groupBy((k, v) -> KeyValue.pair(v.getSiteId(), v),
                Grouped.with(longSerde, onlineSummarySerde))
                .aggregate(() -> new Long(0),
                        (k, v, agg) -> v.getOnline() == true ? agg.longValue() + 1 : agg,
                        (k, v, agg) -> v.getOnline() == true ? agg.longValue() - 1 : agg,
                        Materialized.<Long,Long,KeyValueStore<Bytes, byte[]>>as(ONLINE_AMOUNT_STORE).withKeySerde(longSerde).withValueSerde(longSerde));
        amountTable.toStream().print(Printed.<Long, Long>toSysOut().withLabel("amount-table")
                .withKeyValueMapper((k,v)->String.format("siteId:%d,amount:%d",k,v)));
        Topology topology = builder.build();
//        System.out.println(topology.describe());
        kafkaStreams = new KafkaStreams(topology, streamsConfig);
    }

    //    @PostConstruct
    public void stop() {
        kafkaStreams.close();
    }

    public static void main(String[] args) {
        DeviceOnlineService service=new DeviceOnlineService();
        service.createStreams();
        service.reset();
    }

    private void reset() {
        // cleanUp 重置了local state store
        kafkaStreams.cleanUp();
    }

    public Long query(String store,Long key) {
        if(store.equals(ONLINE_STORE)){
            boolean online=onlineStore.get(key).getOnline();
            return online==true?1L:0L;
        }else{
            return onlineAmountStore.get(key);
        }
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "DevOnlineSvc");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "eh-Group");
////        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "eh-Client");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "3000");

        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1000");
        //将缓存设为0，导致ktable无法工作
        //props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG,"0");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "zrylovestan.com:9092");
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, "1");

        props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "10000");
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);

//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StreamsSerdes.StockTickerSerde().getClass().getName());
//        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        return props;
    }
}
