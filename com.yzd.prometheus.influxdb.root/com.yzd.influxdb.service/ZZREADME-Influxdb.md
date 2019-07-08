###　Prometheus中rate和irate的区别
- [Prometheus中rate和irate的区别](https://www.zhukun.net/archives/8301)-函数计算一段时间范围内某个时刻的每秒增长率
- influxdb 写法
```
eg: GC COUNT
SELECT LAST("young.gc.count") -FIRST("young.gc.count") FROM "microservice_status" WHERE ("serviceId" =~ /^$app$/) and time > now() - 1h GROUP BY time(30s) fill(null)
```
> **开源数据库InfluxDB常用函数**

- [InfluxDB 的函数详解](https://blog.csdn.net/zx711166/article/details/84381077)
- [开源数据库InfluxDB常用函数](https://blog.csdn.net/weixin_36135773/article/details/78789443)
```
SPREAD()函数
返回字段的最小值和最大值之间的差值。数据的类型必须是长整型或float64。
语法：
SELECT SPREAD(<field_key>) FROM <measurement_name> [WHERE <stuff>] [GROUP BY <stuff>]
--------------------- 
使用场景：
计算：GC COUNT(垃圾回收次数)，GC SUM(垃圾回收时间)

SELECT SPREAD("process_uptime_seconds") FROM "microservice_status" WHERE time > now() - 5m GROUP BY time(10s)
```