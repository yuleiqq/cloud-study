package com.example.flowablespringboot;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormModel;
import org.flowable.form.api.FormService;
import org.flowable.form.engine.FormEngine;
import org.flowable.form.engine.FormEngineConfiguration;
import org.flowable.form.engine.configurator.FormEngineConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yulei
 * @create: 2022-09-23
 * @Version 1.0
 **/
public class TestFlowWithForm {

    ProcessEngineConfiguration configuration = null;

    ProcessEngine processEngine =null;

    FormEngine formEngine = null;
    FormEngineConfiguration formConfig =null;

    @Before
    public void before(){

        configuration =  new StandaloneProcessEngineConfiguration();
        //配置相关数据库的连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("Ddos-1234");
        configuration.setJdbcUrl("jdbc:mysql://192.168.61.49:3306/flowdb");
        //如果数据库的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //1. 获取processEngine 对象
        processEngine = configuration.buildProcessEngine();

        formConfig = FormEngineConfiguration.createStandaloneFormEngineConfiguration();
        formConfig.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        formConfig.setJdbcUsername("root");
        formConfig.setJdbcPassword("Ddos-1234");
        formConfig.setJdbcUrl("jdbc:mysql://192.168.61.49:3306/flowdb");
        //如果数据库的表结构不存在就新建
        formConfig.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        formEngine = formConfig.buildFormEngine();
        FormEngineConfigurator formEngineConfigurator = new FormEngineConfigurator();
        formEngineConfigurator.setFormEngineConfiguration(formConfig);
        formEngineConfigurator.configure(formConfig);

        configuration.addConfigurator(formEngineConfigurator);

    }


    /**
     * 流程定义的部署
     */
    @Test
    public void deploy(){
        System.out.println("部署流程...");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("form.bpmn20.xml")
                .deploy();
        System.out.println("deployment.getId() = " + deployment.getId());

        //deployment.getId() = 17501
    }


    @Test
    public void start(){
        //启动实例并且设置表单的值
        String outcome = "shareniu";
        Map formProperties ;
        formProperties = new HashMap<>();
        formProperties.put("reason", "家里有事");
        formProperties.put("startTime", "2022-09-12");
        formProperties.put("endTime", "2022-09-23");
        //实例变量名
        String processInstanceName = "ttttt";
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceWithForm("form-test:1:17503", null, formProperties, processInstanceName);
    }

    @Test
    public void getForm(){


        RuntimeService runtimeService = processEngine.getRuntimeService();

        //查询表单信息

        FormInfo fm = runtimeService.getStartFormModel("form-test:1:17503", "1fa5fae1-3ba4-11ed-a59a-66d1fb2e5786");

        System.out.println("获取了表单信息:");

        System.out.println(fm.getId());

        System.out.println(fm.getKey());

        System.out.println(fm.getName());

        System.err.println(fm.getVersion());

        FormModel formModel = fm.getFormModel();


    }





}
