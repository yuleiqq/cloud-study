package com.example.flowablespringboot;

import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.form.api.FormDefinition;
import org.flowable.form.api.FormDeployment;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.api.FormService;
import org.flowable.form.engine.FormEngine;
import org.flowable.form.engine.FormEngineConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Form 引擎的测试
 *
 **/
public class TestForm {

       FormEngine formEngine = null;
       FormEngineConfiguration formConfig =null;

    @Before
    public void before(){
        formConfig = FormEngineConfiguration.createStandaloneFormEngineConfiguration();
        formConfig.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        formConfig.setJdbcUsername("root");
        formConfig.setJdbcPassword("Ddos-1234");
        formConfig.setJdbcUrl("jdbc:mysql://192.168.61.49:3306/flowdb");
        //如果数据库的表结构不存在就新建
        formConfig.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        formEngine = formConfig.buildFormEngine();
    }




    @Test
    public void deploy(){

        FormDeployment deploy = formEngine.getFormRepositoryService().createDeployment()
                .addClasspathResource("test.form").deploy();
        //deploy.getId() = 4cbfd988-3b4f-11ed-852f-1a339d595823
        System.out.println("deploy.getId() = " + deploy.getId());
    }


    /**
     * 查询表单定义
     */
    @Test
    public  void testForm(){

        FormRepositoryService formRepositoryService = formEngine.getFormRepositoryService();
        FormDefinition formDefinition = formRepositoryService.createFormDefinitionQuery().deploymentId("4cbfd988-3b4f-11ed-852f-1a339d595823").singleResult();

        //表单定义
        System.out.println("formDefinition.getId() = " + formDefinition.getId());


    }




}
