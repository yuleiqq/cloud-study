**docker 部署 clickhouse**

```sh
docker run -d --name  dd_clickhouse --ulimit nofile=262144:262144 \
-p 8123:8123 -p 9000:9000 -p 9009:9009 --privileged=true \
-v /Users/yulei/docker/clickhouse/log:/var/log/clickhouse-server \
-v /Users/yulei/docker/clickhouse/data:/var/lib/clickhouse clickhouse/clickhouse-server:22.2.3.5
```

- **默认http端口是8123，tcp端口是9000, 同步端口9009**



## MergeTree 引擎

```sql
--创建一个MergeTree 引擎的表
create table order_merge_tree(
id UInt32,
    sku_id String,
    out_trade_no String ,
    total_amount Decimal(16,2),
    create_time DateTime
)  engine  = MergeTree()
   order by  (id,sku_id)
   partition by toYYYYMM(create_time)
   primary key (id);
```

插入数据

```sql
insert into xdclass_shop.order_merge_tree values 
(1,'sku_1','aabbcc',5600.00,'2023-03-01 16:00:00') ,
(2,'sku_2','23241',4.02,'2023-03-01 17:00:00'),
(3,'sku_3','542323',55.02,'2023-03-01 18:00:00'), 
(4,'sku_1','54222',2000.3,'2023-04-01 19:00:00'), 
(5,'sku_2','53423',120.2,'2023-04-01 19:00:00'), 
(6,'sku_4','65432',600.01,'2023-04-02 11:00:00');
```


