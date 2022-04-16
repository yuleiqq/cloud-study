package com.example.flinkdemo.sink;

import com.example.flinkdemo.model.VideoOrder;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MysqlSink extends RichSinkFunction<VideoOrder> {

    private Connection conn = null;
    private PreparedStatement ps = null;

    /**
     * 初始化连接
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters)
            throws Exception {
        System.out.println("--open-");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/redis_db?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=Asia/Shanghai", "root", "12345678");
        String sql = "INSERT INTO `video_order` (`user_id`, `money`, `title`, `trade_no`,`create_time`)VALUES( ?,?,?,?,?);";
        ps = conn.prepareStatement(sql);
    }


    /**
     * 关闭连接
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        System.out.println("---close-----");
        if (conn != null) {
            conn.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    @Override
    public void invoke(VideoOrder videoOrder, Context context) throws Exception {
        //给ps中的?设置具体值
          ps.setInt(1,videoOrder.getUserId());
          ps.setInt(2,videoOrder.getMoney());
          ps.setString(3,videoOrder.getTitle());
          ps.setString(4,videoOrder.getTradeNo());
          ps.setDate(5,new Date(videoOrder.getCreateTime().getTime()));
          ps.executeUpdate();
    }

}