package com.yzd.influxdb.service.utils;

import cn.hutool.core.io.file.FileReader;
import com.yzd.influxdb.service.entities.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MetricsUtil {
    public static void getMetricsList2(String metricsResponse) {
        metricsResponse = RegExUtil.ereg_replace("# [^\\n\\r]+[\\n\\r]+", "", metricsResponse);
        String[] itemList = StringUtils.split(metricsResponse, "[\r\n]");
        for (String item : itemList) {
            System.out.println(item);
            if (StringUtils.isBlank(item)) {
                continue;
            }
            String numStr = RegExUtil.fetchStr("[\\d\\.E]+$", item);
            System.out.println(numStr);
            boolean isNum = NumberUtils.isCreatable(numStr);
            System.out.println(isNum);
            if (BooleanUtils.isNotTrue(isNum)) {
                continue;
            }
            //Prometheus JVM_Export的数据大部分是科学计数据：2.7262976E7 显示：27262976
            BigDecimal decimalVal = NumberUtils.createBigDecimal(numStr);
            System.out.println("number.BigDecimal()=" + decimalVal.toPlainString());
        }
        System.out.println("=========");
        //判断是否包括指标：不区分字母大小写的方式
        int firstIndex = StringUtils.indexOfIgnoreCase("a11", "A");
        System.out.println(firstIndex);
        if (firstIndex == 0) {
            System.out.println("在字符串首位");
        }
        System.out.println("=========");
    }

    public static List<String> getMetricsList(String metricsResponse) {
        metricsResponse = RegExUtil.ereg_replace("# [^\\n\\r]+[\\n\\r]+", "", metricsResponse);
        String[] itemList = StringUtils.split(metricsResponse, "[\r\n]");
        return Arrays.asList(itemList);
    }

    public static String getNumber(String metrics) {
        if (StringUtils.isBlank(metrics)) {
            return null;
        }
        String numStr = RegExUtil.fetchStr("[\\d\\.E]+$", metrics);
        log.debug(numStr);
        boolean isNum = NumberUtils.isCreatable(numStr);
        log.debug(String.valueOf(isNum));
        if (BooleanUtils.isNotTrue(isNum)) {
            return null;
        }
        //Prometheus JVM_Export的数据大部分是科学计数据：2.7262976E7 显示：27262976
        BigDecimal decimalVal = NumberUtils.createBigDecimal(numStr);
        log.debug("number.BigDecimal()=" + decimalVal.toPlainString());
        return decimalVal.toPlainString();
    }
    public static List<Metrics> readMetricsConfig() {
        List<Metrics> itemList=new ArrayList<>();
        itemList.add(new Metrics("process_uptime_seconds","process_uptime_seconds","在线时长"));
        itemList.add(new Metrics("system_cpu_usage","system_cpu_usage","CPU使用率"));
        itemList.add(new Metrics("jvm_threads_live","jvm_threads_live","实时线程"));
        itemList.add(new Metrics("jvm_threads_daemon","jvm_threads_daemon","守护线程"));
        itemList.add(new Metrics("process_start_time_seconds","process_start_time_seconds","启动时间"));
        itemList.add(new Metrics("heap_memory_used_survivor","jvm_memory_used_bytes{area=\"heap\",id=\"PS Survivor Space\",}","survivor区-已使用内存"));
        itemList.add(new Metrics("heap_memory_used_eden","jvm_memory_used_bytes{area=\"heap\",id=\"PS Eden Space\",}","eden区-已使用内存"));
        itemList.add(new Metrics("heap_memory_used_old","jvm_memory_used_bytes{area=\"heap\",id=\"PS Old Gen\",}","old区-已使用内存"));
        itemList.add(new Metrics("heap_memory_max_survivor","jvm_memory_max_bytes{area=\"heap\",id=\"PS Survivor Space\",}","survivor区-最大内存"));
        itemList.add(new Metrics("heap_memory_max_eden","jvm_memory_max_bytes{area=\"heap\",id=\"PS Eden Space\",}","eden区-最大内存"));
        itemList.add(new Metrics("heap_memory_max_old","jvm_memory_max_bytes{area=\"heap\",id=\"PS Old Gen\",}","old区-最大内存"));
        itemList.add(new Metrics("noheap_memory_used_metaspace","jvm_memory_used_bytes{area=\"nonheap\",id=\"Metaspace\",}","metaspace区-已使用内存"));
        itemList.add(new Metrics("noheap_memory_used_code_cache","jvm_memory_used_bytes{area=\"nonheap\",id=\"Code Cache\",}","code_cache区-已使用内存"));
        itemList.add(new Metrics("noheap_memory_used_compressed","jvm_memory_used_bytes{area=\"nonheap\",id=\"Compressed Class Space\",}","compressed区-已使用内存"));
        itemList.add(new Metrics("noheap_memory_max_metaspace","jvm_memory_max_bytes{area=\"nonheap\",id=\"Metaspace\",}","metaspace区-最大内存"));
        itemList.add(new Metrics("noheap_memory_max_code_cache","jvm_memory_max_bytes{area=\"nonheap\",id=\"Code Cache\",}","code_cache区-最大内存"));
        itemList.add(new Metrics("noheap_memory_max_compressed","jvm_memory_max_bytes{area=\"nonheap\",id=\"Compressed Class Space\",}","compressed区-最大内存"));
        return itemList;
    }
    /*public static List<Metrics> readMetricsConfig() {
        FileReader fileReader = new FileReader("prometheus-jvm-config.json");
        String result = fileReader.readString();
        return FastJsonUtil.deserializeList(result,Metrics.class);
    }*/
}
