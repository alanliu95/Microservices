package com.alan.microservices.event.handler.utils;

import java.util.regex.Pattern;

public class ExRegUtil {
    public static void main(String[] args) {
        String pattern = "/site/[a-zA-Z0-9]+/status";
        String text = "/site/clien124tId/status";
        System.out.println(Pattern.matches(pattern, text));
    }
}
