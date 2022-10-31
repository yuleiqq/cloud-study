package ch1;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;

/**
 * @author: yulei
 * @create: 2022-10-10
 * @Version 1.0
 **/
public class TestOne {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();//一直往下debug
        System.out.println("流程名称：" + processEngine.getName());
        System.out.println("配置类：" + processEngine.getProcessEngineConfiguration());
        System.out.println("鉴权：" + processEngine.getAuthorizationService());
        System.out.println("外部任务：" + processEngine.getExternalTaskService());
        System.out.println("过滤器：" + processEngine.getFilterService());

        System.out.println(processEngine.getFormService());
        System.out.println(processEngine.getHistoryService());

        System.out.println(processEngine.getIdentityService());
        System.out.println(processEngine.getManagementService());
        System.out.println(processEngine.getRepositoryService());
        System.out.println(processEngine.getRuntimeService());
        System.out.println(processEngine.getTaskService());
    }
}
