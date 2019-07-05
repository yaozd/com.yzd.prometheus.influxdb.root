package com.yzd.consul.service.consul;

import com.yzd.consul.service.entities.MonitorType;
import com.yzd.consul.service.entities.ServiceInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import startup.Application4Consul;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application4Consul.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ConsulServiceTest {

    @Autowired
    IConsulService consulService;
    ServiceInfo serviceInfo;
    @Before
    public void init() {
        String name = "com.example.actuator";
        String ip = "127.0.0.1";
        Integer port = 8080;
        Long interval = 3l;
        serviceInfo = ServiceInfo.builder()
                .name(name)
                .ip(ip)
                .port(port)
                .checkUrl(ip + ":" + port)
                .checkInterval(interval)
                .tag(MonitorType.M_JVM.name())
                .build();
    }

    @Test
    public void add() {
        consulService.add(serviceInfo);
    }

    @Test
    public void getAllHealthyServiceByServiceName() {
        consulService.getAllHealthyServiceByServiceName(serviceInfo.getName());
    }
    @Test
    public void getAllHealthyServiceByServiceTag() {
        consulService.getAllHealthyServiceByServiceTag(MonitorType.M_JVM.name());
    }
}