package com.example.flowablespringboot.controller;

import com.example.flowablespringboot.service.MyService;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormModel;
import org.flowable.form.model.SimpleFormModel;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yulei
 * @create: 2022-08-19
 * @Version 1.0
 **/

@RestController
public class MyRestController {

    @Autowired
    private MyService myService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    ProcessEngine processEngine;

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public void startProcessInstance() {
        myService.startProcess();
    }


    @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }


    static  class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }



    /**
     * 获取流程图
     */

    @RequestMapping(value = "/getImg")
    public void genProcessDiagram4(HttpServletResponse httpServletResponse, @RequestParam(name="processInstanceId") String processInstanceId) throws Exception {

        // 根据流程实例Id查询该流程实例的具体信息
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        // 获得流程定义Id
        String processDefinitionId;
        // 流程走完的从历史中获取
        if (processInstance == null) {
            //return;
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
        } else {
               processDefinitionId = processInstance.getProcessDefinitionId();
        }
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        if (task == null) {
            //return;
        }
        // 根据流程实例Id查询该流程实例正在执行的活动
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        // 得到正在执行的活动Id
        List<String> activityIds = new ArrayList<>();

        List<String> flows = new ArrayList<>();
        for (Execution execution : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(execution.getId());
            activityIds.addAll(ids);
        }
        // 获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessEngineConfiguration engineConfiguration = processEngine.getProcessEngineConfiguration();


        ProcessDiagramGenerator diagramGenerator = engineConfiguration.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel,
                "png",
                activityIds,
                flows,
                engineConfiguration.getActivityFontName(),
                engineConfiguration.getLabelFontName(),
                engineConfiguration.getAnnotationFontName(),
                engineConfiguration.getClassLoader(),
                1.0,
                true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int length = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping("/start")
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



    @RequestMapping("/getForm")
    public void getForm(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //查询表单信息
        FormInfo fm = runtimeService.getStartFormModel("form-test:1:17503", "1fa5fae1-3ba4-11ed-a59a-66d1fb2e5786");
        System.out.println("获取了表单信息:");
        System.out.println(fm.getId());
        System.out.println(fm.getKey());
        System.out.println(fm.getName());
        System.err.println(fm.getVersion());
        SimpleFormModel formModel = (SimpleFormModel) fm.getFormModel();


    }

}
