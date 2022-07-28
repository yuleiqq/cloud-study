package com.example.flinkdemo.my;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 *   Flink Kafka Consumer 的所有版本都具有上述明确的起始位置配置方法。
 *
 * setStartFromGroupOffsets（默认方法）：从 Kafka brokers 中的 consumer 组（consumer 属性中的 group.id 设置）提交的偏移量中开始读取分区。
 *       如果找不到分区的偏移量，那么将会使用配置中的 auto.offset.reset 设置。
 * setStartFromEarliest() 或者 setStartFromLatest()：从最早或者最新的记录开始消费，在这些模式下，Kafka 中的 committed offset 将被忽略，不会用作起始位置。
 * setStartFromTimestamp(long)：从指定的时间戳开始。对于每个分区，其时间戳大于或等于指定时间戳的记录将用作起始位置。如果一个分区的最新
 **/
public class FlinkKafkaDemo {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "test");

        FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>("input", new SimpleStringSchema(), properties);
        myConsumer.setStartFromLatest();       // 从最新的记录开始
//        myConsumer.setStartFromTimestamp(...); // 从指定的时间开始（毫秒）
//        myConsumer.setStartFromGroupOffsets(); // 默认的方法

        DataStream<String> stream = env
                .addSource(myConsumer);

        stream.print();

        env.execute("kakka");

    }

}
