package com.yulei.flowable.test;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yulei
 * @create: 2022-06-14
 * @Version 1.0
 *
 * 获取流程引擎对象
 **/
public class Test01 {

    ProcessEngineConfiguration configuration =  new StandaloneInMemProcessEngineConfiguration();


    @Test
    public void testProcessEngine(){
        
         configuration =  new StandaloneInMemProcessEngineConfiguration();
        //配置相关数据库的连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("12345678");
        configuration.setJdbcUrl("jdbc:mysql:///flowable-learn?serverTimezone=UTC");

        //如果数据库的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = configuration.buildProcessEngine();

    }


    @Before
    public void before(){

        configuration =  new StandaloneInMemProcessEngineConfiguration();
        //配置相关数据库的连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("12345678");
        configuration.setJdbcUrl("jdbc:mysql:///flowable-learn?serverTimezone=UTC");

        //如果数据库的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    }


    /**
     * 部署流程
     */
    @Test
    public void deploy(){
        //1. 获取processEngine 对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        //2. 获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.获取
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .name("请假流程").deploy();

        System.out.println("deployment.id = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());

    }


    /**
     * 查询
     */
    @Test
    public void testDeployQuery(){
        //1. 获取processEngine 对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        ProcessDefinition processDefinition = processDefinitionQuery.deploymentId("1").singleResult();

        System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());
        System.out.println("processDefinition.getName() = " + processDefinition.getName());
        System.out.println("processDefinition.getDescription() = " + processDefinition.getDescription());
        System.out.println("processDefinition.getId() = " + processDefinition.getId());

    }

    /**
     * 删除流程定义
     */

    @Test
    public void testDeleteDeploy(){
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //删除部署的流程，第一个参数是ID， 如果部署的流程启动了就不允许删除了
        //第二个参数是级联删除，如果流程启动了相关的任务一并会被删除掉
        repositoryService.deleteDeployment("10001",true);

    }

    /**
     * 10001
     *
     * 启动流程实例
     *
     */

    @Test
    public void testRunProcess(){

        ProcessEngine processEngine = configuration.buildProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //构建流程变量
        Map<String,Object> var = new HashMap<>();
        var.put("name","张三");
        var.put("days",3);
        var.put("desc","工作累了");
        //启动流程实例
        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("holidayRequest", var);

        System.out.println("holidayRequest.getProcessDefinitionId() = " + holidayRequest.getProcessDefinitionId());
        System.out.println("holidayRequest.getActivityId() = " + holidayRequest.getActivityId());
        System.out.println("holidayRequest.getId() = " + holidayRequest.getId());
    }


    /**
     * 查询任务
     */

    @Test
    public void testQueryTask(){
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holidayRequest") //指定查询流程编号
                .taskAssignee("zhangsan")  //查询这个任务的处理人
                .list();

        for(Task task : list){
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("task.getName() = " + task.getName());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getDescription() = " + task.getDescription());
            System.out.println("task.getId() = " + task.getId());
        }

    }

    /**
     * 完成任务
     */
    @Test
    public void testComplete(){

        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holidayRequest") //指定查询流程编号
                .taskAssignee("zhangsan")  //查询这个任务的处理人
                .singleResult();

        //创建流程变量
        Map<String,Object> map = new HashMap<>();
        map.put("approved",false);
        //完成任务
        taskService.complete(task.getId(),map);

    }

    /**
     * 查看历史
     */

    @Test
    public void testHistroyQuery(){
        ProcessEngine processEngine = configuration.buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();

        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processDefinitionId("holidayRequest:1:15003") // 流程定义ID
                .finished()  //查询历史任务的状态是完成的
                .orderByHistoricActivityInstanceEndTime().asc() // 指定排序的字段 和顺序
                .list();
        for(HistoricActivityInstance histroy : list){

            System.out.println(histroy.getActivityId() +": "+ histroy.getAssignee());
        }


    }






}
