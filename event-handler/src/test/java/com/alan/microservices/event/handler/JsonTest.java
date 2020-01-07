package com.alan.microservices.event.handler;

import com.alan.microservices.commons.Result;
import com.alan.microservices.commons.asset.domain.Device;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String str = "{\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": null,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"siteId\": 1,\n" +
                "            \"name\": \"desktop-alan\",\n" +
                "            \"token\": \"123456\",\n" +
                "            \"devType\": 2,\n" +
                "            \"devTypeName\": \"windows主机\",\n" +
                "            \"longitude\": 31.146,\n" +
                "            \"latitude\": 121.421,\n" +
                "            \"info\": \"windows10工作站\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"siteId\": 1,\n" +
                "            \"name\": \"ubuntu_14th\",\n" +
                "            \"token\": \"ubuntu_14th\",\n" +
                "            \"devType\": 1,\n" +
                "            \"devTypeName\": \"ubuntu服务器\",\n" +
                "            \"longitude\": null,\n" +
                "            \"latitude\": null,\n" +
                "            \"info\": \"k8s集群worker节点\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"siteId\": 1,\n" +
                "            \"name\": \"vm-ubuntu16\",\n" +
                "            \"token\": \"ubuntu16\",\n" +
                "            \"devType\": 3,\n" +
                "            \"devTypeName\": \"vmware\",\n" +
                "            \"longitude\": null,\n" +
                "            \"latitude\": null,\n" +
                "            \"info\": \"k8s集群master节点\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Result<ArrayList<Device>> obj = mapper.readValue(str, Result.class);
        System.out.println(obj);
    }
}
