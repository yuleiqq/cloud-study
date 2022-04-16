package com.example.flinkdemo.source;

import com.example.flinkdemo.model.VideoOrder;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *  自定义Source
 *
 * @author: yulei
 * @create: 2022-04-15
 * @Version 1.0
 **/
public class VideoOrderSource  extends RichParallelSourceFunction<VideoOrder> {

    private volatile Boolean flag = true;
    private Random random = new Random();
    private static List<String> list = new ArrayList<>();
    static {
        list.add("spring boot2.x课程");
        list.add("微服务SpringCloud课程");
        list.add("RabbitMQ消息队列");
        list.add("Kafka课程");
        list.add("Flink流式技术课程");
        list.add("Linux课程");
    }


    /**
     * 用于初始化连接
     *
     *  @Author yulei
     *  @Date  2022/4/15
     *  @Param 
     *  @return 
     **/
    @Override
    public void open(Configuration parameters) throws Exception {
        System.out.println("----open-----");
    }

    @Override
    public void close() throws Exception {
        System.out.println("-----close-----");
    }


    /**
     * 产生数据的逻辑
     * @param ctx
     * @throws Exception
     */
    @Override
    public void run(SourceContext<VideoOrder> ctx) throws Exception {

        while (flag){

              TimeUnit.SECONDS.sleep(1);
              String id = UUID.randomUUID().toString();
              int userId = random.nextInt(10);
              int money = random.nextInt(100);
              int videoNum = random.nextInt(list.size());
              String title = list.get(videoNum);
              VideoOrder videoOrder = new VideoOrder(id,title,money,userId,new Date());
              ctx.collect(videoOrder);
         }
    }


    /**
     *
     * 用于清理之前
     *  @Author yulei
     *  @Date  2022/4/15
     *  @Param
     *  @return
     **/
    @Override
    public void cancel() {
        flag =false;
    }
}
