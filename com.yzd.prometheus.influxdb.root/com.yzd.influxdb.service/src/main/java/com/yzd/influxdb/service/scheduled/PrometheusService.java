package com.yzd.influxdb.service.scheduled;

import com.yzd.influxdb.service.entities.Metrics;
import com.yzd.influxdb.service.entities.ServiceInfo;

import java.util.List;

public interface PrometheusService {
    List<Metrics> getMetrics(String url);
    void writeMetrics(ServiceInfo serviceInfo);
}
