package com.yzd.influxdb.service.scheduled;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.resource.ClassPathResource;
import com.yzd.influxdb.service.entities.Metrics;
import com.yzd.influxdb.service.entities.ServiceInfo;
import com.yzd.influxdb.service.influxdb.IInfluxDbService;
import com.yzd.influxdb.service.utils.FastJsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import startup.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PrometheusServiceTest {

    @Autowired
    PrometheusService prometheusService;
    @Autowired
    IInfluxDbService iInfluxDbService;

    /**
     * 解决prometheus的指标信息
     */
    @Test
    public void getMetrics() {
        String url = "http://localhost:8080";
        prometheusService.getMetrics(url);

    }

    /**
     *
     */
    @Test
    public void writeMetrics() {
        String url = "http://localhost:8080";
        ServiceInfo serviceInfo=new ServiceInfo();
        serviceInfo.setName("yzd-1");
        serviceInfo.setUrl(url);
        //
        List<Metrics> itemList4MetricsEntity= prometheusService.getMetrics(serviceInfo.getUrl());
        HashMap<String,String> tags = new HashMap<>() ;
        tags.put("service.name",serviceInfo.getName()) ;
        HashMap<String,Object> fields = new HashMap<>();
        for(Metrics item:itemList4MetricsEntity){
            fields.put(item.getName(),Double.parseDouble(item.getValue()));
        }
        iInfluxDbService.writeMetrics(tags,fields);
    }
    /**
     * 读取与创建配置文件
     */
    @Test
    public void readConfig() {
        List<Metrics> itemList=new ArrayList<>();
        //在线时长
        itemList.add(new Metrics("uptime","process_uptime_seconds","在线时长"));
        //CPU使用率
        itemList.add(new Metrics("system.cpu.usage","system_cpu_usage","CPU使用率"));
        //实时线程
        itemList.add(new Metrics("jvm.threads.live","jvm_threads_live","实时线程"));
        //守护线程
        itemList.add(new Metrics("jvm.threads.daemon","jvm_threads_daemon","守护线程"));
        //itemList.add(new Metrics("uptime","process_uptime_seconds",""));
        //itemList.add(new Metrics("uptime","process_uptime_seconds",""));
        String json=FastJsonUtil.serialize(itemList);
        FileReader fileReader = new FileReader("prometheus-jvm-config.json");
        String result = fileReader.readString();
    }
}