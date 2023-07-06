package com.jianhaoweb.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Auther:剑豪
 * @Date:2023/6/8
 * @VERSON:1.8
 */
public class Test1 {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        Instant instant = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        String timestamp = ldt.format(formatter);
        System.out.println(timestamp);
    }
}
