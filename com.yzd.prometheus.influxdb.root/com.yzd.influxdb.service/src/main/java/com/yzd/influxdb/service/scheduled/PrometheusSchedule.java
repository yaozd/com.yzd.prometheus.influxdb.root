package com.yzd.influxdb.service.scheduled;

import com.yzd.influxdb.service.entities.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 任务调度
 */
@Component
public class PrometheusSchedule {
    @Autowired
    PrometheusService prometheusService;

    //拉取指标，5/S
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void doWork() {
        String url = "http://localhost:8080";
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setName("yzd-1");
        serviceInfo.setUrl(url);
        //
        prometheusService.writeMetrics(serviceInfo);
    }
}
