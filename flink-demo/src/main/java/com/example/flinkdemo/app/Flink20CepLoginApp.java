package com.example.flinkdemo.app;


import com.example.flinkdemo.util.TimeUtil;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


public class Flink20CepLoginApp {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //tom ,2021-12-01 12:00:21,-1
        DataStreamSource<String> ds = env.socketTextStream("127.0.0.1", 8888);
        SingleOutputStreamOperator<Tuple3<String,String,Integer>> flatMap = ds.flatMap(new FlatMapFunction<String, Tuple3<String,String,Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple3<String,String,Integer>> out) throws Exception {
                String arr[] = value.split(",");
                out.collect(Tuple3.of(arr[0], arr[1], Integer.parseInt(arr[2])));

            }
        });

        flatMap.assignTimestampsAndWatermarks(WatermarkStrategy.<Tuple3<String,String,Integer>>forMonotonousTimestamps().withTimestampAssigner((event,timestamp)->{

            return TimeUtil.strToDate(event.f1).getTime();
        }));


    }

}
