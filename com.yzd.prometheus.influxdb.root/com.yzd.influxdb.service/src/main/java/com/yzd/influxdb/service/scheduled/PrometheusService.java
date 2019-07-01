package com.yzd.influxdb.service.scheduled;

import com.yzd.influxdb.service.entities.Metrics;

import java.util.List;

public interface PrometheusService {
    List<Metrics> getMetrics(String url);
}
