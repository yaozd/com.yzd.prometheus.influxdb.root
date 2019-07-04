package com.yzd.influxdb.service.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.yzd.influxdb.service"})
public class Application4UnitTest {

    public static void main(String[] args) {
        SpringApplication.run(Application4UnitTest.class, args);
    }
}