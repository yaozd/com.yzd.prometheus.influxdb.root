package com.yzd.consul.service.consul;

import com.yzd.consul.service.entities.ServiceInfo;

public interface IConsulService {
    void add(ServiceInfo serviceInfo);

    void delete(String serviceId);

    /**
     * 通过服务名称找到健康的服务
     * @param serviceName
     */
    void getAllHealthyServiceByServiceName(String serviceName);

    /**
     * 通过服务标签找到健康的服务
     * @param serviceTag
     */
    void getAllHealthyServiceByServiceTag(String serviceTag);
}
