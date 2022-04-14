package com.example.flinkdemo.app;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;




/**
 * 根据字符串的逗号进行分割，输出
 * @author: yulei
 * @create: 2022-04-14
 * @Version 1.0
 **/
public class Flink01App
{
    public static void main(String [] args) throws Exception {

        //构建执行任务环境以及任务的启动的入口, 存储全局相关的参数
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //设置并行度
        env.setParallelism(1 );

        //相同类型元素的数据流 source
        DataStream<String> stringDS = env.fromElements("java,SpringBoot", "spring cloud,redis", "kafka");

        stringDS.print("处理前");

        // FlatMapFunction<String, String>, key是输入类型，value是Collector响应的收集的类型，看源码注释，也是 DataStream<String>里面泛型类型
        DataStream<String> flatMapDS = stringDS.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> collector) throws Exception {

                String [] arr =  value.split(",");
                for(String str : arr){
                    collector.collect(str);
                }
            }
        });

        //输出 sink
        flatMapDS.print("处理后");

        //DataStream需要调用execute,可以取个名称
        env.execute("flat map job");
    }
}
