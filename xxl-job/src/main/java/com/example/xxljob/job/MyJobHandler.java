package com.example.xxljob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: yulei
 * @create: 2022-06-13
 * @Version 1.0
 **/

@Component
public class MyJobHandler {

    private Logger log  = LoggerFactory.getLogger(MyJobHandler.class);

    @XxlJob(value = "demoJobHandler",init = "init",destroy = "destroy")
    public void execute(){
        String param = XxlJobHelper.getJobParam();
        System.out.println("param: = " + param);
        log.info("淘宝 execute 任务触发成功："+ LocalDateTime.now());
        XxlJobHelper.handleFail("执行失败！");

    }

    private  void init(){

        log.info("初始化成功!");
    }

    private  void destroy(){
        log.info("销毁成功!");
    }


}
