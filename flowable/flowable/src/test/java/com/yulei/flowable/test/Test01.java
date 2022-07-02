package com.yulei.flowable.test;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: yulei
 * @create: 2022-06-14
 * @Version 1.0
 *
 * 获取流程引擎对象
 **/
public class Test01 {

    @Test
    public void testProcessEngine(){
        
        ProcessEngineConfiguration configuration =  new StandaloneInMemProcessEngineConfiguration();
        //配置相关数据库的连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("12345678");
        configuration.setJdbcUrl("jdbc:mysql:///flowable-learn?serverTimezone=UTC");

        //如果数据库的表结构不存在就新建s
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = configuration.buildProcessEngine();

    }


    @Before
    public void before(){

        ProcessEngineConfiguration configuration =  new StandaloneInMemProcessEngineConfiguration();
        //配置相关数据库的连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("12345678");
        configuration.setJdbcUrl("jdbc:mysql:///flowable-learn?serverTimezone=UTC");

        //如果数据库的表结构不存在就新建s
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//

    }



}
