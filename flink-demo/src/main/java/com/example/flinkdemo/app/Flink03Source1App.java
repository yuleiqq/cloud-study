package com.example.flinkdemo.app;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 *
 *
 * Source 1
 * @author: yulei
 * @create: 2022-04-15
 * @Version 1.0
 **/
public class Flink03Source1App {

    public static void main(String[] args) throws Exception {

        //构建执行任务环境以及任务的启动的入口, 存储全局相关的参数
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //相同元素类型的数据流
        DataStreamSource<String> ds1 = env.fromElements("java,SpringBoot", "spring cloud, redis");
        ds1.print("ds1:");  // print 也是 sink 的一种


        //相同元素类型的数据流
        DataStreamSource<String> ds2 = env.fromCollection(Arrays.asList("java,SpringBoot", "spring cloud, redis"));
        ds2.print("ds2:");

        env.execute("flat map job");





    }

}
