## Consul
> 作用：用于服务的注册

```
* 最终确定的写法通过tcp方式来进行检查
* //如果在服务注册的时候使用的是http方式，则getResponse中会带有大量的响应信息-output。使用传输的数据变大
* //因此推荐使用TCP方式进行服务的检查
* //目前的功能主要是consul作为prometheus的注册发现,jmx_exporter输出的信息比较多,所以推荐tcp注册方式
* //https://github.com/prometheus/jmx_exporter
```

### consul官网
-[https://www.consul.io/downloads.html](https://www.consul.io/downloads.html)
### 下载备份
> 百度云=》开发工个=》C-Counsul服务发现

### 2.Consul-INSTALL

1.Consul-run.bat
```
//-dev 代表是开发模板不存储在磁盘，数据存储在内存
consul agent -dev
```
2.Consul-open-consul.bat
```
//Windows dos下
start http://127.0.0.1:8500/ui/dc1/services
```