package com.example.flinkdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: yulei
 * @create: 2022-04-15
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoOrder {

    private String tradeNo;

    private String title;

    private int money;

    private int userId;

    private Date createTime;

}
