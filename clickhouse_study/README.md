**docker 部署 clickhouse**

```sh
docker run -d --name  dd_clickhouse --ulimit nofile=262144:262144 \
-p 8123:8123 -p 9000:9000 -p 9009:9009 --privileged=true \
-v /Users/yulei/docker/clickhouse/log:/var/log/clickhouse-server \
-v /Users/yulei/docker/clickhouse/data:/var/lib/clickhouse clickhouse/clickhouse-server:22.2.3.5
```

- **默认http端口是8123，tcp端口是9000, 同步端口9009**



**区分大小写**

 不同CH版本存在不一样的数据类型，毕竟是火热项目，有些尽管新增但是也很少没用上
 case_insensitive 选项为1 表示大小写不敏感，字段类型不区分大小写
 为0 表示大小写敏感，即字段类型需要严格区分大小写
 里面很多数据类型，记住常用的即可

```
select * from system.data_type_families
```

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
insert into order_merge_tree values 
(1,'sku_1','aabbcc',5600.00,'2023-03-01 16:00:00') ,
(2,'sku_2','23241',4.02,'2023-03-01 17:00:00'),
(3,'sku_3','542323',55.02,'2023-03-01 18:00:00'), 
(4,'sku_1','54222',2000.3,'2023-04-01 19:00:00'), 
(5,'sku_2','53423',120.2,'2023-04-01 19:00:00'), 
(6,'sku_4','65432',600.01,'2023-04-02 11:00:00');
```



**分区合并验证**

登录容器，连接命令   clickhouse-client

新的数据写入会有临时分区产生，不之间加入已有分区

写入完成后经过一定时间（10到15分钟），ClickHouse会自动化执行合并操作，将临时分区的数据合并到已有分区当中

optimize的合并操作是在后台执行的，无法预测具体执行时间点，除非是手动执行

通过手工合并( optimize table xxx final; ) 

在数据量比较大的情况，尽量不要使用该命令，执行optimize要消耗大量时间















