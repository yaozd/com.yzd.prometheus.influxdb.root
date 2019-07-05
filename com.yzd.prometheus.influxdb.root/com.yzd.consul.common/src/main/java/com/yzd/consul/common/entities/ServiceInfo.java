package com.yzd.consul.common.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceInfo {
    //serviceId相当记录的唯一标识，如果id相同则会覆盖。
    private String id;
    private String name;
    private String ip;
    private Integer port;
    private String tag;
    private String checkUrl;
    private Long checkInterval;

    public String getId() {
        return this.name + "-" + this.ip;
    }
}
