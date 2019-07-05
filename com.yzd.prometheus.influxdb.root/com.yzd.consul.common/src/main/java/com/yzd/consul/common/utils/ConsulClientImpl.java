package com.yzd.consul.common.utils;

import cn.hutool.http.HttpUtil;
import com.yzd.consul.common.entities.ServiceInfo;

import java.util.HashMap;
import java.util.List;

public class ConsulClientImpl implements IConsulClientInf {
    private String url;

    public ConsulClientImpl(String url) {
        this.url = url;
    }

    @Override
    public List<ServiceInfo> getAllHealthyServiceByServiceTag(String serviceTag) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("serviceTag", serviceTag);
        String result = HttpUtil.get(url + "/consul/getAllHealthyServiceByServiceTag", paramMap);
        return FastJsonUtil.deserializeList(result, ServiceInfo.class);
    }

    @Override
    public String add(ServiceInfo serviceInfo) {
        return HttpUtil.post(url + "/consul/add", FastJsonUtil.serialize(serviceInfo));
    }
}
