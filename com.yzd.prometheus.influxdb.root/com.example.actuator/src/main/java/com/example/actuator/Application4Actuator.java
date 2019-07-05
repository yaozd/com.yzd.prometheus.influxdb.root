package com.example.actuator;

import com.example.actuator.config.ApplicationEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application4Actuator {

    public static ConfigurableApplicationContext context;

    /**
     * 测试地址：
     * http://localhost:8080/test/logInfo
     * http://localhost:8080/test/logError
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application4Actuator.class);
        app.addListeners(new ApplicationEventListener());
        context =app.run(args);
    }
}
