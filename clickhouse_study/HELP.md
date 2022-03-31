







**docker 部署 clickhouse**

```
docker run -d --name  dd_clickhouse --ulimit nofile=262144:262144 \
-p 8123:8123 -p 9000:9000 -p 9009:9009 --privileged=true \
-v /Users/yulei/docker/clickhouse/log:/var/log/clickhouse-server \
-v /Users/yulei/docker/clickhouse/data:/var/lib/clickhouse clickhouse/clickhouse-server:22.2.3.5
```

- 默认http端口是8123，tcp端口是9000, 同步端口9009

  
