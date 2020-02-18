package com.alan.microservices.event.handler.serde;




import com.alan.microservices.event.handler.model.OnlineEvent;
import com.alan.microservices.event.handler.model.OnlineSummary;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class StreamsSerdes {
    public static Serde<OnlineEvent> OnlineEventSerde() {
        return new OnlineEventSerde();
    }

    public static Serde<OnlineSummary> OnlineSummarySerde() {
        return new OnlineSummarySerde();
    }

    public static final class OnlineEventSerde extends WrapperSerde<OnlineEvent> {
        public OnlineEventSerde(){
            super(new JsonSerializer<>(), new JsonDeserializer<>(OnlineEvent.class) );
        }
    }

    public static final class OnlineSummarySerde extends WrapperSerde<OnlineSummary> {
        public OnlineSummarySerde(){
            super(new JsonSerializer<>(), new JsonDeserializer<>(OnlineSummary.class) );
        }
    }

    private static class WrapperSerde<T> implements Serde<T> {

        private JsonSerializer<T> serializer;
        private JsonDeserializer<T> deserializer;

         WrapperSerde(JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
            this.serializer = serializer;
            this.deserializer = deserializer;
        }

        @Override
        public void configure(Map<String, ?> map, boolean b) {

        }

        @Override
        public void close() {

        }

        @Override
        public Serializer<T> serializer() {
           return serializer;
        }

        @Override
        public Deserializer<T> deserializer() {
           return deserializer;
        }
    }
}
