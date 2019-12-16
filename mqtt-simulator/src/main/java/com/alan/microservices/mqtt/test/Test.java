package com.alan.microservices.mqtt.test;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        String s = "a";
        list.add(s);
        s = "ab";
        list.stream().forEach(e -> System.out.println(e));
    }
}
