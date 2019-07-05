package com.yzd.influxdb.service.scheduled;

import com.yzd.consul.common.entities.MonitorType;
import com.yzd.consul.common.entities.ServiceInfo;
import com.yzd.consul.common.utils.ConsulClientImpl;
import com.yzd.consul.common.utils.IConsulClientInf;
import com.yzd.influxdb.service.entities.ApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 任务调度
 */
@Component
public class PrometheusSchedule {
    @Value("${info.consul.url}")
    private String consulUrl;
    @Autowired
    PrometheusService prometheusService;

    //拉取指标，5/S
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void doWork() {
        //通过服务标签找到健康的服务
        IConsulClientInf consulClientInf = new ConsulClientImpl(consulUrl);
        List<ServiceInfo> list4ServiceInfo= consulClientInf.getAllHealthyServiceByServiceTag(MonitorType.M_JVM.name());
        for (ServiceInfo item4ServiceInfo:list4ServiceInfo) {
            ApplicationInfo applicationInfo=ApplicationInfo.toApplicationInfo(item4ServiceInfo);
            prometheusService.writeMetrics(applicationInfo);
        }
    }
}
