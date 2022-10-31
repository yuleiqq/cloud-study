package com.example.camundademo1;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.FormProperty;
import org.camunda.bpm.engine.form.StartFormData;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: yulei
 * @create: 2022-10-09
 * @Version 1.0
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProcess {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    FormService formService;

    @Autowired
    TaskService taskService;


    /**
     * 外置表单部署
     */
    @Test
    public void createDeployment() {
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("外置表单测试")
                .source("本地测试")
                .tenantId("a")
                .addClasspathResource("ch18/form.bpmn")
                .addClasspathResource("ch18/start.html")
                .addClasspathResource("ch18/1.html")
                .addClasspathResource("ch18/2.html")
                .deploy();

        System.out.println(deploymentBuilder);
        System.out.println(deployment);
    }


    @Test
    public void start(){

        //启动
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday-process");

        System.out.println("processInstance.getProcessInstanceId() = " + processInstance.getProcessInstanceId());

    }


    @Test
    public void getStartFormKey() {
        String processDefinitionId="holiday-process:2:0f7b02bc-494e-11ed-b3ee-c69a12a40f6b";
        String startFormKey = formService.getStartFormKey(processDefinitionId);
        System.out.println(startFormKey);

    }


    @Test
    public void getTaskFormKey() {
        String processDefinitionId="holiday-process:2:0f7b02bc-494e-11ed-b3ee-c69a12a40f6b";
        String taskDefinitionKey = "Activity_0rtic2t";
        String startFormKey = formService.getTaskFormKey(processDefinitionId, taskDefinitionKey);
        System.out.println(startFormKey);
    }


    /**
     * 渲染节点信息
     */
    @Test
    public void getRenderedStartForm() {
        String processDefinitionId="holiday-process:4:79f1f7f0-4952-11ed-bd90-c69a12a40f6b";
        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId, "juel");
        System.out.println(renderedStartForm);
    }


    @Test
    public void submitStartForm() {

        String processDefinitionId="holiday-process:4:79f1f7f0-4952-11ed-bd90-c69a12a40f6b";
        VariableMap variableMap = Variables.createVariables()
                .putValue("days", 10)
                .putValue("reason", "请假三天")
                .putValue("startUserId", "张三")
                .putValue("category", "年假");
        //通过form 启动流程实例
        ProcessInstance processInstance = formService.submitStartForm(processDefinitionId, variableMap);
        System.out.println(processInstance);
    }

    /**
     * 获取启动的表单实例
     */

    @Test
    public void getStartFormData(){

        String processDefinitionId="holiday-process:4:79f1f7f0-4952-11ed-bd90-c69a12a40f6b";

        StartFormData startFormData = formService.getStartFormData(processDefinitionId);

        System.out.println(startFormData);

    }








    @Test
    public void getTaskFormData() {
        Task task = taskService.createTaskQuery().taskId("5411").singleResult();
        System.out.println(task.getId());

        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        System.out.println("############");
        System.out.println(taskFormData.getDeploymentId());
        System.out.println(taskFormData.getTask());
        System.out.println(taskFormData.getFormKey());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        System.out.println(formProperties);
        List<FormField> formFields = taskFormData.getFormFields();
        System.out.println(formFields);
        System.out.println("############");
    }


}
