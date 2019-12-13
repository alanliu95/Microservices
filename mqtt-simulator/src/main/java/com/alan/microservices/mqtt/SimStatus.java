package com.alan.microservices.mqtt;

import org.codehaus.jackson.map.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SimStatus extends SysStatus implements MessGenerator {

    private Random ra;
    private ObjectMapper mapper;

    //	public SimStatus(String deviceId) {
//		super(deviceId);
//		this.ra = new Random();
//	}
    public SimStatus(String deviceId, ObjectMapper mapper) {
        super(deviceId);
        this.ra = new Random();
        this.mapper = mapper;
    }

    public void readStatus() {
        setMem(ra.nextFloat() * 100);
        setCpu(ra.nextFloat() * 100);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// 设置日期格式
        setTs(df.format(new Date()));// new Date()为获取当前系统时间，也可使用当前时间戳
    }

    @Override
    public byte[] oneMessage() {
        readStatus();
        byte[] byteArr = null;
        try {
            byteArr = mapper.writeValueAsBytes(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArr;
    }

}
