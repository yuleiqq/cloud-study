**docker 部署 clickhouse**

```sh
docker run -d --name  dd_clickhouse --ulimit nofile=262144:262144 \
-p 8123:8123 -p 9000:9000 -p 9009:9009 --privileged=true \
-v /mydata/docker/clickhouse/log:/var/log/clickhouse-server \
-v /mydata/docker/clickhouse/data:/var/lib/clickhouse clickhouse/clickhouse-server:22.2.3.5
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





# 和SpringBoot 的项目整合

Maven 依赖

```xml

        <dependency>
            <groupId>ru.yandex.clickhouse</groupId>
            <artifactId>clickhouse-jdbc</artifactId>
            <version>0.1.55</version>
        </dependency>

        <!--mybatis plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.0</version>
        </dependency>

```

application.yml 配置

```yml
server:
  port: 8080

spring:
  datasource:
    driver-class-name: ru.yandex.clickhouse.ClickHouseDriver
    url: jdbc:clickhouse://8.140.98.27:8123/default
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
logging:
  level:
     root: INFO

```



创建表

```sql
CREATE TABLE default.visit_stats
(
    `product_id` UInt64,
    `is_new` UInt16,
    `province` String,
    `city` String,
    `pv` UInt32,
    `visit_time` DateTime
)
ENGINE = MergeTree()
PARTITION BY toYYYYMMDD(visit_time)
ORDER BY (
 product_id,
 is_new,
 province,
 city
 );
```

插入数据

```sql

INSERT into visit_stats values
('1','1','广东','广州',14323,'2023-01-01 12:11:13'),
('1','0','广东','广州',4232,'2023-02-12 16:16:13'),
('1','1','广东','佛山',54323,'2023-03-06 16:11:13'),
('1','0','广东','东莞',42341,'2023-03-02 16:12:13'),
('1','1','广东','梅州',52422,'2023-03-09 12:11:13'),

('2','1','广东','广州',14323,'2021-03-01 12:11:13'),
('2','0','广东','深圳',425232,'2023-04-12 16:16:13'),
('2','1','广东','佛山',543323,'2022-06-06 16:11:13'),
('2','0','广东','东莞',42341,'2021-05-02 16:12:13'),
('2','1','广东','梅州',52422,'2022-01-09 12:11:13'),

('3','1','北京','北京',13132,'2023-01-01 12:11:13'),
('3','0','广东','广州',533232,'2022-02-16 16:16:13'),
('4','1','浙江','杭州',663643,'2023-12-06 12:11:13'),
('4','0','广东','东莞',4142,'2023-11-02 16:12:13'),
('5','1','湖南','长沙',52123,'2022-01-09 12:11:13'),
('4','0','湖南','衡阳',4142,'2024-05-02 16:12:13'),
('5','1','广东','中山',52123,'2024-01-09 12:11:13'),

('2','1','上海','上海',14323,'2021-03-01 12:11:13'),
('5','0','浙江','宁波',425232,'2023-04-12 16:16:13'),
('3','1','广东','佛山',543323,'2022-06-06 16:11:13'),
('2','0','湖南','长沙',42341,'2021-05-02 16:12:13'),
('2','1','广东','深圳',52422,'2022-01-09 12:11:13')
```



**统计需求**

* 某个商品再时间范围内地区访问分布-城市级别  

```
select province,city, sum(pv) pv_count  
from visit_stats where  product_id =1 
and toYYYYMMDD(visit_time) BETWEEN '20200101' and '20241212' 
group by province,city order by pv_count desc
```

* 函数（ClickHouse还有很多SQL函数，我们只讲常用的，其他可以百度【clickhouse函数】或官方文档）

  * 求和

    ```
    sum(pv) 
    ```

  * 年格式

    ```
    select toYear(toDateTime('2024-12-11 11:12:13')) 
    ```

  * 日期格式化

    ```
    select toYYYYMMDD(toDateTime('2024-12-11 11:12:13')) 
    ```

  * 日期时间格式化

    ```
    select toYYYYMMDDhhmmss(toDateTime('2024-12-11 11:12:13')) 
    ```

  * 周格式化,1~7，当前时间是本周第几天，下面是周三结果是3，周日结果是7

    ```
    select toDayOfWeek(toDateTime('2024-12-11 11:12:13')) 
    ```

  * 小时格式化，提取时间里面的小时，比如 2023-12-29 10:05:10，格式化后是【10】点

    ```
    select toHour(toDateTime('2024-12-11 11:12:13')) 
    ```

  * 分钟格式化，提取时间里面的分钟，比如 2023-12-29 10:05:10，格式化后是【5】分钟

    ```sql
    select toMinute(toDateTime('2024-12-11 11:12:13')) 
    ```

    

  * 秒格式化，提取时间里面的秒

    ```sql
    select toSecond(toDateTime('2024-12-11 11:12:13')) 
    ```

    

  * 获取当前日期时间

    ```sql
    select now()
    ```

  * 获取当前日期

    ```sql
    select today()
    ```



