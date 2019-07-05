package com.yzd.consul.service.controller;

import com.yzd.consul.common.entities.ServiceInfo;
import com.yzd.consul.service.consul.IConsulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("consul")
public class ConsulController {
    @Autowired
    IConsulService consulService;

    @RequestMapping("add")
    public String add(@RequestBody ServiceInfo serviceInfo) {
        log.info("add");
        consulService.add(serviceInfo);
        return "ok";
    }

    @RequestMapping("getAllHealthyServiceByServiceName")
    public List<ServiceInfo> getAllHealthyServiceByServiceName(String serviceTag) {
        log.info("getAllHealthyServiceByServiceName");
        return consulService.getAllHealthyServiceByServiceName(serviceTag);
    }

    @RequestMapping("getAllHealthyServiceByServiceTag")
    public List<ServiceInfo> getAllHealthyServiceByServiceTag(String serviceTag) {
        log.info("getAllHealthyServiceByServiceTag");
        return consulService.getAllHealthyServiceByServiceTag(serviceTag);
    }

}
