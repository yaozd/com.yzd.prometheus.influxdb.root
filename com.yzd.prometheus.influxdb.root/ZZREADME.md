### 服务说明
- com.example.actuator：测试用例（服务注册与取消）
- com.yzd.consul.service:(提供consul的http接口，用于第三方使用)
- com.yzd.influxdb.service（应用指标的抓取）

### 项目中使用的相关技术
- consul
- influxdb
- grafana
- prometheus
- 

### 项目启动顺序
1. influxdb
2. consul
3. grafana
4. com.yzd.consul.service
5. com.yzd.influxdb.service
6. com.example.actuator


### 基本思路
- actuator=>启动=》注册=》consul
- actuator=>关闭=》删除注册=》consul
- influxdb=>拉取有效的服务=》读取Prometheus指标=》存储到influxdb=>Grafana展示