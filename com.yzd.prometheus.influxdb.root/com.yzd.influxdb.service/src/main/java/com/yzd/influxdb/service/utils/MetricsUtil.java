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
        log.info(numStr);
        boolean isNum = NumberUtils.isCreatable(numStr);
        log.info(String.valueOf(isNum));
        if (BooleanUtils.isNotTrue(isNum)) {
            return null;
        }
        //Prometheus JVM_Export的数据大部分是科学计数据：2.7262976E7 显示：27262976
        BigDecimal decimalVal = NumberUtils.createBigDecimal(numStr);
        log.info("number.BigDecimal()=" + decimalVal.toPlainString());
        return decimalVal.toPlainString();
    }

    public static List<Metrics> readMetricsConfig() {
        FileReader fileReader = new FileReader("prometheus-jvm-config.json");
        String result = fileReader.readString();
        return FastJsonUtil.deserializeList(result,Metrics.class);
        //List<Metrics> itemList=new ArrayList<>();
        //itemList.add(new Metrics("uptime","process_uptime_seconds",""));
        //return itemList;
    }
}
