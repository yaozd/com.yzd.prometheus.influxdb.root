###　Prometheus中rate和irate的区别
- [Prometheus中rate和irate的区别](https://www.zhukun.net/archives/8301)-函数计算一段时间范围内某个时刻的每秒增长率
- influxdb 写法
```
eg: GC COUNT
SELECT LAST("young.gc.count") -FIRST("young.gc.count") FROM "microservice_status" WHERE ("serviceId" =~ /^$app$/) and time > now() - 1h GROUP BY time(30s) fill(null)
```