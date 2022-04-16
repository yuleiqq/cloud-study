package com.example.flinkdemo.app;

import com.example.flinkdemo.model.VideoOrder;
import com.example.flinkdemo.source.VideoOrderSource;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author: yulei
 * @create: 2022-04-15
 * @Version 1.0
 **/
public class Flink05Sink1App {


    public static void main(String[] args) throws Exception {
        //构建执行任务环境以及任务的启动的入口, 存储全局相关的参数
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);

        DataStream<VideoOrder> videoOrderDS = env.addSource(new VideoOrderSource());

        videoOrderDS.filter(new FilterFunction<VideoOrder>() {
            @Override
            public boolean filter(VideoOrder value) throws Exception {
                return value.getMoney() > 5;
            }
        });

        //红色模式打印
        videoOrderDS.printToErr();

        env.execute("sink job ");

    }


}
