package com.example.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActuatorApplication {

    /**
     * 测试地址：
     * http://localhost:8080/test/logInfo
     * http://localhost:8080/test/logError
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }
}
