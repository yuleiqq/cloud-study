package com.example.flinkdemo.app;

import com.example.flinkdemo.model.VideoOrder;
import com.example.flinkdemo.source.VideoOrderSource;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author: yulei
 * @create: 2022-04-15
 * @Version 1.0
 **/
public class Flink04CustomSourceApp {


    public static void main(String[] args) throws Exception {
        //构建执行任务环境以及任务的启动的入口, 存储全局相关的参数
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

//        env.setParallelism(1);


        DataStream<VideoOrder> videoOrderDS = env.addSource(new VideoOrderSource());

        videoOrderDS.print();

        env.execute("source job ");

    }





}
