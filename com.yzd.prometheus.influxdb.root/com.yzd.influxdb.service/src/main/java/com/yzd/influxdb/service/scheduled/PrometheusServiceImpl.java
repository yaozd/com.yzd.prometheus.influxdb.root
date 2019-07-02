package com.yzd.influxdb.service.scheduled;

import com.yzd.influxdb.service.entities.Metrics;
import com.yzd.influxdb.service.restclient.IRestClient;
import com.yzd.influxdb.service.utils.MetricsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrometheusServiceImpl implements PrometheusService {
    @Autowired
    private IRestClient iRestClient;

    @Override
    public List<Metrics> getMetrics(String url) {
        String metricsResponse = iRestClient.get(url + "/actuator/prometheus");
        List<String> itemList4MetricsResponse = MetricsUtil.getMetricsList(metricsResponse);
        List<Metrics> itemList4MetricsEntity = MetricsUtil.readMetricsConfig();
        for (Metrics item : itemList4MetricsEntity) {
            String metricsStr=itemList4MetricsResponse.stream().filter(m->m.contains(item.getPattern())).findFirst().orElse(null);
            if(metricsStr==null){
                continue;
            }
            item.setValue(MetricsUtil.getNumber(metricsStr));
        }
        return itemList4MetricsEntity;
    }
}