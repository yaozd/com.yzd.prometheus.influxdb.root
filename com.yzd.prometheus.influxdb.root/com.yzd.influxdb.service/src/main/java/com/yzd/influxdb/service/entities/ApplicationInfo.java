package com.yzd.influxdb.service.entities;

import com.yzd.consul.common.entities.ServiceInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationInfo {
    private String id;
    private String name;
    private String url;
    private String tag;
    public static ApplicationInfo toApplicationInfo(ServiceInfo serviceInfo){
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.setId(serviceInfo.getIp());
        applicationInfo.setName(serviceInfo.getName());
        applicationInfo.setUrl(String.format("http://%s:%d",serviceInfo.getIp(),serviceInfo.getPort()));
        applicationInfo.setTag(serviceInfo.getTag());
        return applicationInfo;
    }
    public static ApplicationInfo toApplicationInfo(String name,String ip,Integer port,String tag){
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.setId(ip);
        applicationInfo.setName(name);
        applicationInfo.setUrl(String.format("http://%s:%d",ip,port));
        applicationInfo.setTag(tag);
        return applicationInfo;
    }
}
