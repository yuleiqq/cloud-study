package com.example.flinkdemo.app;

import org.apache.flink.api.java.tuple.Tuple3;

/**
 *
 * 最多支持25个 ,本示例演示 支持3个
 *
 * @author: yulei
 * @create: 2022-04-14
 * @Version 1.0
 **/
public class TupleApp {

    public static void main(String[] args) {

        Tuple3 tuple3 = Tuple3.of(1,"xd",1L);

        System.out.println(tuple3.f2);


    }

}
