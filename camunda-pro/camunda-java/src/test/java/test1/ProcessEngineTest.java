package test1;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程引擎
 */


public class ProcessEngineTest {

    ProcessEngine processEngine =null;
    RuntimeService runtimeService;
    RepositoryService repositoryService;



    @Before
    public void before(){
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        //设置历史级别
        processEngineConfiguration.setHistory(ProcessEngineConfiguration.HISTORY_FULL);

         processEngineConfiguration .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/camunda666")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setJdbcUsername("root")
                .setJdbcPassword("12345678")
                .setJobExecutorActivate(true);

         processEngine = processEngineConfiguration.buildProcessEngine();
         runtimeService = processEngine.getRuntimeService();
         repositoryService = processEngine.getRepositoryService();

    }

    /**
     * 流程定义查询
     */

    @Test
     public void testProcessDefineQuery(){

         List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                 .processDefinitionKey("spring-demo")
                 .orderByProcessDefinitionVersion()
                 .asc()
                 .list();

         for(ProcessDefinition processDefinition : processDefinitions){
             System.out.println("processDefinition.getId() = " + processDefinition.getId());
             System.out.println("processDefinition.getName() = " + processDefinition.getName());
             System.out.println("=====================================");

         }
     }



    /**
     * 启动流程实例
     */
    @Test
    public void startProcess(){

        //流程变量
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("name", "yulei");
        //启动
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("spring-demo", variables);

    }


    /**
     * 查询流程实例
     */
    @Test
    public void queryProcessIntances(){

        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();

        for(ProcessInstance processInstance : list){

            System.out.println("processInstance.getId() = " + processInstance.getId());
        }

    }



}
