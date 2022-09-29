package com.example.flowablespringboot.service;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *  创建一个新的Spring服务类，并创建两个方法：一个用于启动流程，另一个用于获得给定任务办理人的任务列表。
 *  在这里只是简单地包装了Flowable调用，但在实际使用场景中会比这复杂得多。
 */
@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private  RepositoryService repositoryService;

    @Autowired
    ProcessEngine processEngine;

    /**
     * 启动流程
     */
    @Transactional
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    /**
     * 用于获得给定任务办理人的任务列表
     * @param assignee
     * @return
     */
    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }








}