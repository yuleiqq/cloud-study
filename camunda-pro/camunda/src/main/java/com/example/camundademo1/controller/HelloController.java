package com.example.camundademo1.controller;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//             佛祖保佑       永不宕机     永无BUG                  //


@RestController
public class HelloController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;


    @Autowired
    FormService formService;

    /**
     * 资源的部署
     */
    @RequestMapping("/deploy")
    public String  deploy(@RequestParam(value = "res" ,required = true) String res){

        if(StringUtils.isEmpty(res)){
            return "参数不能为空";
        }

        System.out.println("即将部署的资源："+ res);

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource(res)
                .deploy();

        return  res +" 部署成功";
    }

    /**
     * 启动流程
     */
    @PostMapping("/start")
    public String startProcess(){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_1hbo1wh");

        return  "流程启动成功！" + processInstance.getProcessInstanceId();

    }


    /**
     * 查询并完成任务
     */

    @RequestMapping("/taskQuery")
    public void taskQuery(){

        List<Task> list = taskService.createTaskQuery().active()
                .list();

        for(Task task : list){
            System.out.println("task.getName() = " + task.getName());

            TaskFormData taskFormData = formService.getTaskFormData(task.getId());

            VariableMap taskFormVariables = formService.getTaskFormVariables(task.getId());

            System.out.println("获取任务数据结束");


        }
    }

    @RequestMapping("/taskComplete")
    public void taskComplete(@RequestParam(value = "taskId" ,required = true) String taskId){

        taskService.complete(taskId);
    }









}
