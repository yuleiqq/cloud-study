package com.example.flowablespringboot;


import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: yulei
 * @create: 2022-08-20
 * @Version 1.0
 **/
public class TestFlowable {

    ProcessEngineConfiguration configuration = null;

    ProcessEngine processEngine =null;

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
    }

    /**
     * 流程定义的部署
     */
    @Test
    public void deploy(){

        System.out.println("部署流程...");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("my.bpmn20.xml")
                .deploy();

        System.out.println("deployment.getId() = " + deployment.getId());

    }


    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefine(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Deployment> list = repositoryService.createDeploymentQuery().orderByDeploymentId().asc().list();
        for(Deployment deployment : list){

            System.out.println("部署的ID："+ deployment.getId()+",部署的名称:"+deployment.getName());

        }
        //流程定义的查询
        List<ProcessDefinition> processDefiList = repositoryService.createProcessDefinitionQuery().list();

        for (ProcessDefinition processDefinition : processDefiList) {

            System.out.println("processDefinition.getId() = " + processDefinition.getId());
            System.out.println("processDefinition.getName() = " + processDefinition.getName());
        }


    }








    /**
     * 启动流程实例
     */

    @Test
    public void startProcess(){

        //模拟前端的输入信息
        Scanner scanner= new Scanner(System.in);
        System.out.println("Who are you?");
        String employee = scanner.nextLine();
        System.out.println("How many holidays do you want to request?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());
        System.out.println("Why do you need them?");
        String description = scanner.nextLine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
//      ProcessInstance financialReport = runtimeService.startProcessInstanceByKey("financialReport");

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);

        //流程实例启动时，传递流程变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("holidayRequest:1:5004",variables);

        //获取流程实例的ID  processInstance.getId() = 10001
        System.out.println("processInstance.getId() = " + processInstance.getId());


    }

    @Test
    public void taskQuery(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().deploymentId("5001").list();

        for(Task task : tasks ){
            System.out.println("task.getName() = " + task.getName());
            //指定任务的处理人
            taskService.setAssignee(task.getId(),"pangyao");
        }
    }


    /**
     *  可以使用任务Id获取特定流程实例的变量，并在屏幕上显示实际的申请
     */
    @Test
    public void queryTaskVariables(){

        TaskService taskService = processEngine.getTaskService();
        System.out.println("获取任务变量");
        Map<String, Object> processVariables = taskService.getVariables("10009");
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");

    }

    /**
     * 完成指定搞得任务
     */

    @Test
    public void completeTask(){
        System.out.println("是否批准: ");
        Scanner scanner= new Scanner(System.in);
        TaskService taskService = processEngine.getTaskService();
        boolean approved = scanner.nextLine().toLowerCase().equals("y");
        Map  variables = new HashMap<String, Object>();
        variables.put("approved", approved);
        //实际过程中，任务ID，可以由前端传递
        taskService.complete("10009", variables);

    }



    /**
     * 查询历史活动实例
     */
    @Test
    public void queryHistory(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId("10001").finished().list();

        for(HistoricActivityInstance activity: list){


            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }

    }

    @Test
    public void historyTask(){

        HistoryService historyService = processEngine.getHistoryService();

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().finished().list();

        for(HistoricTaskInstance historicTaskInstance : list){

            System.out.println("historicTaskInstance.getName() = " + historicTaskInstance.getName());
            System.out.println("historicTaskInstance.getEndTime() = " + historicTaskInstance.getEndTime());
        }


    }










}
