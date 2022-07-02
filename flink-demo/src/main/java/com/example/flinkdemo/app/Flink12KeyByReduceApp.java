package com.example.flinkdemo.app;

import com.example.flinkdemo.model.VideoOrder;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Date;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description 流处理
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

public class Flink12KeyByReduceApp {

    /**
     * source
     * transformation
     * sink
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        //构建执行任务环境以及任务的启动的入口, 存储全局相关的参数
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        env.setParallelism(1);

        //数据源 source
        DataStream<VideoOrder> ds = env.fromElements(
                new VideoOrder("21312","java",32,5,new Date()),
                new VideoOrder("314","java",32,5,new Date()),
                new VideoOrder("542","springboot",45,5,new Date()),
                new VideoOrder("42","redis",12,5,new Date()),
                new VideoOrder("4252","java",32,5,new Date()),
                new VideoOrder("42","springboot",45,5,new Date()),
                new VideoOrder("554232","flink",30,5,new Date()),
                new VideoOrder("23323","java",32,5,new Date())
        );

        //DataStreamSource<VideoOrder> ds = env.addSource(new VideoOrderSourceV2());

        KeyedStream<VideoOrder, String> videoOrderStringKeyedStream = ds.keyBy(new KeySelector<VideoOrder, String>() {
            @Override
            public String getKey(VideoOrder value) throws Exception {
                return value.getTitle();
            }
        });


        SingleOutputStreamOperator<VideoOrder> reduce = videoOrderStringKeyedStream.reduce(new ReduceFunction<VideoOrder>() {
            @Override
            public VideoOrder reduce(VideoOrder value1, VideoOrder value2) throws Exception {

                VideoOrder videoOrder = new VideoOrder();
                videoOrder.setTitle(value1.getTitle());
                videoOrder.setMoney(value1.getMoney() + value2.getMoney());
                return videoOrder;
            }
        });

        reduce.print();

        //SingleOutputStreamOperator<VideoOrder> money = videoOrderStringKeyedStream.sum("money");
        //money.print();

        //DataStream需要调用execute,可以取个名称
        env.execute("map job");
    }

}
