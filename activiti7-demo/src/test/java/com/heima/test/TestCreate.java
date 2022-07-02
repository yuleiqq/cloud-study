package com.heima.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.junit.Test;


/**
 * @author: yulei
 * @create: 2022-06-14
 * @Version 1.0
 **/
public class TestCreate {

    /**
     * 使用activity提供的默认方式来创建mysql的表
     */

    @Test
    public void testCreateDbTable(){

        /**
         * 工作流引擎的创建
         */

        //需要使用activity 提供的工具类 , 会默认从resources 下读取名字为activiti.cfg.xml 的文件
        //创建爱你processEngine时，就会创建mysql的表
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        System.out.println(processEngine);


        //使用自定义方式, 配置文件名字可以自定义
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("");
        ProcessEngine processEngine1 = configuration.buildProcessEngine();
        System.out.println(processEngine1);

        RuntimeService runtimeService = processEngine1.getRuntimeService();


    }





}
