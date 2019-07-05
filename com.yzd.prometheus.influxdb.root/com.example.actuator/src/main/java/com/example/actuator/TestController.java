package com.example.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("logInfo")
    public String logInfo() {
        log.info("logInfo");
        return "logInfo";
    }

    @RequestMapping("logError")
    public String logError() {
        log.error("logError");
        return "logError";
    }
}
