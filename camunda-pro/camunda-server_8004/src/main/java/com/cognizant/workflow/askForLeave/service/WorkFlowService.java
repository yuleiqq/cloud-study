package com.cognizant.workflow.askForLeave.service;

import com.cognizant.dto.ResponseData;
import com.cognizant.workflow.askForLeave.bean.*;
import com.cognizant.workflow.askForLeave.dto.SupplyDto;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricDetail;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkFlowService {


    @Autowired
    RepositoryService repositoryService;

    public ResponseData deploy(SupplyDto supplyDto){
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("askForLeave.bpmn").deploy();
        return new ResponseData(0);
    }

    @Autowired
    RuntimeService runtimeService ;
    @Autowired
    TaskService taskService;
    @Autowired
    IdentityService identityService;

    public ResponseData start(AskForLeaveStartBean startBean, SupplyDto supplyDto){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("startDate",startBean.getStartDate());
        stringObjectHashMap.put("endDate",startBean.getEndDate());
        stringObjectHashMap.put("reason",startBean.getReason());
        stringObjectHashMap.put("applier",supplyDto.getUserName());
        stringObjectHashMap.put("createdAt",new Date());
        ProcessInstance process_askForLeave = runtimeService.startProcessInstanceByKey("Process_askForLeave", stringObjectHashMap);
        TaskQuery taskQuery = taskService.createTaskQuery().processInstanceId(process_askForLeave.getId());
        List<Task> list = taskQuery.list();
        if(!CollectionUtils.isEmpty(list)){
            list.get(0).setAssignee(startBean.getApprover());
            taskService.saveTask( list.get(0));
        }
        return new ResponseData(0);
    }

    public ResponseData list(AskForLeaveSearchBean bean, SupplyDto supplyDto){
        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey("Process_askForLeave")
                .taskAssignee(supplyDto.getUserName());
        List<Task> list = taskQuery.list();
        List<Object> collect = list.stream().map(t -> {
            Map<String, Object> variables = runtimeService.getVariables(t.getProcessInstanceId());
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            map.put("taskId", t.getId());
            map.put("taskName", t.getName());
            map.put("startDate",variables.get("startDate"));
            map.put("endDate", variables.get("endDate"));
            map.put("reason",  variables.get("reason"));
            map.put("applier", variables.get("applier"));
            map.put("createdAt",variables.get("createdAt"));
            return map;
        }).collect(Collectors.toList());
        return new ResponseData(0,null, collect);
    }

    public ResponseData myTaskList(AskForLeaveSearchBean bean, SupplyDto supplyDto){
        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey("Process_askForLeave");
        List<Task> list = taskQuery.list();
        List<Object> collect = list.stream().map(t -> {
            Map<String, Object> variables = runtimeService.getVariables(t.getProcessInstanceId());
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            map.put("taskId", t.getId());
            map.put("taskName", t.getName());
            map.put("startDate",variables.get("startDate"));
            map.put("endDate", variables.get("endDate"));
            map.put("reason",  variables.get("reason"));
            Object applier = variables.get("applier");
            map.put("applier", variables.get("applier"));
            if(applier!=null && !applier.equals(supplyDto.getUserName())){
                return null;
            }else if(t.getAssignee()==null || t.getAssignee().trim().length()==0){
                return null;
            }
            map.put("createdAt",variables.get("createdAt"));
            return map;
        }).filter(t->t!=null).collect(Collectors.toList());
        return new ResponseData(0,null, collect);
    }



    public ResponseData approve(AskForLeaveApproveBean idBean, SupplyDto supplyDto){
//        identityService.setAuthenticatedUserId(idBean.getApprover());
//        identityService.setAuthenticatedUserId(supplyDto.getUserName());
        List<Task> list = taskService.createTaskQuery().taskId(idBean.getTaskId()).list();
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseData(1005,"没找到task",null);
        }
        String processInstanceId =list.get(0).getProcessInstanceId();
        taskService.createComment(idBean.getTaskId(), processInstanceId, idBean.getComment());
        taskService.complete(idBean.getTaskId());
        return new ResponseData(0);
    }

    public ResponseData delete(Integer id, SupplyDto supplyDto){
        return new ResponseData(0);
    }


    @Autowired
    HistoryService historyService;
    public ResponseData history(AskForLeaveHistoryBean bean, SupplyDto supplyDto){
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()//历史任务表查询
                .processDefinitionKey("Process_askForLeave")//使用流程实例ID查询
                .finished()
                .taskAssignee(supplyDto.getUserName())
                .list();
        List<Object> collect1 = htiList.stream().map(t -> {
            List<HistoricDetail> list = historyService.createHistoricDetailQuery().processInstanceId(t.getProcessInstanceId()).variableUpdates().list();
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            map.put("taskId", t.getId());
            map.put("taskName", t.getName());
            List<Comment> taskComments = taskService.getTaskComments(t.getId());
            if(!CollectionUtils.isEmpty(taskComments)){
                map.put("comment",  taskComments.get(0).getFullMessage());
            }

            for (HistoricDetail historicDetail : list) {
                if(historicDetail instanceof HistoricDetailVariableInstanceUpdateEntity){
                    HistoricDetailVariableInstanceUpdateEntity var = (HistoricDetailVariableInstanceUpdateEntity) historicDetail;
                    map.put(var.getVariableName(),  var.getValue());
                }
            }
            return map;
        }).collect(Collectors.toList());

        return new ResponseData(0,null,collect1);
    }



}
