package com.example.flinkdemo.my;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 *
 * 单词计数
 *
 *
 * @author: yulei
 * @create: 2022-07-25
 * @Version 1.0
 **/
public class WindowWordCount {


    public static void main(String[] args) throws Exception {


        //LocalStreamEnvironment 在创建它的同一个 JVM 进程中启动 Flink 系统。如果你从 IDE 启动 LocalEnvironment，则可以在代码中设置断点并轻松调试程序。
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        DataStream<Tuple2<String, Integer>> dataStream = env.socketTextStream("", 9999)
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    //对一行数据进行切割x
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                        for (String word : value.split(" ")) {
                            out.collect(new Tuple2<String, Integer>(word, 1));
                        }
                    }
                }).keyBy(value -> value.f0)
                //滚动窗口
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .sum(1);


        dataStream.print();

        env.execute("Window WordCount");

    }

}
