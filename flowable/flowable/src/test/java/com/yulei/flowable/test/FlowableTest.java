package com.yulei.flowable.test;

import org.apache.ibatis.annotations.Param;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: yulei
 * @create: 2022-08-13
 * @Version 1.0
 **/
public class FlowableTest {


    ProcessEngineConfiguration configuration = null;

    ProcessEngine processEngine =null;


    @Before
    public void before(){

        configuration =  new StandaloneProcessEngineConfiguration();
        //配置相关数据库的连接信息
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("12345678");
        configuration.setJdbcUrl("jdbc:mysql:///flowable-learn?serverTimezone=UTC");

        //如果数据库的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //1. 获取processEngine 对象
         processEngine = configuration.buildProcessEngine();
    }


    /**
     * 部署
     *  deployment.id = 27501
     * deployment.getName() = 个性化请假流程
     */
    @Test
     public void deploy(){
         //2. 获取RepositoryService
         RepositoryService repositoryService = processEngine.getRepositoryService();
         //3.获取
         Deployment deployment = repositoryService.createDeployment()
                 .addClasspathResource("myconfig.bpmn20.xml")
                 .name("个性化请假流程").deploy();

         System.out.println("deployment.id = " + deployment.getId());
         System.out.println("deployment.getName() = " + deployment.getName());


     }
    /**
     * 查询流程定义
     */

    @Test
    public void testQueryDefine(){

         processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId("27501")
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

    }

    /**
     * 启动流程实例
     */

    @Test
    public void start(){
        Scanner scanner= new Scanner(System.in);

        System.out.println("Who are you?");
        String employee = scanner.nextLine();

        System.out.println("How many holidays do you want to request?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

        System.out.println("Why do you need them?");
        String description = scanner.nextLine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);

        runtimeService.startProcessInstanceByKey("holidayRequest",variables);

    }






    /**
     * 查询任务
     */

    @Test
    public void testTaskQuery(){
        //1. 获取processEngine 对象
         processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();

        System.out.println("You have " + tasks.size() + " tasks:");

        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName()+", 任务ID: "+ tasks.get(i).getId());
        }

        Scanner scanner= new Scanner(System.in);

        System.out.println("Which task would you like to complete?");
        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");

    }


    /**
     * 完成任务
     */

    @Test
    public void complete(){
        TaskService taskService = processEngine.getTaskService();

        Scanner scanner= new Scanner(System.in);
        boolean approved = scanner.nextLine().toLowerCase().equals("y");
         Map  variables = new HashMap<String, Object>();
         variables.put("approved", approved);
         // 任务ID : 30008
         taskService.complete("30008", variables);
    }

    /**
     * 查询历史数据
     */

    @Test
    public void queryHistroy(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId("30001")  //实例ID
//                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();

        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }

    }













}
