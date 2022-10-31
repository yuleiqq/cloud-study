package com.cognizant.workflow.askForLeave.controller;

import com.cognizant.dto.ResponseData;
import com.cognizant.workflow.askForLeave.bean.*;
import com.cognizant.workflow.askForLeave.dto.SupplyDto;
import com.cognizant.workflow.askForLeave.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/workflow/askForLeave")
@Slf4j
public class WorkFlowController {


    @Autowired
    WorkFlowService workFlowService;

    @GetMapping(value = "/task/list")
    ResponseData list(AskForLeaveSearchBean bean, HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.list(bean,supplyDto);
    }

    @GetMapping(value = "/task/list/mine")
    ResponseData myTaskList(AskForLeaveSearchBean bean, HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.myTaskList(bean,supplyDto);
    }

    @PostMapping(value = "start")
    ResponseData start(@RequestBody AskForLeaveStartBean startBean , HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.start(startBean,supplyDto);
    }

    @PostMapping(value = "deploy")
    ResponseData deploy(HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.deploy(supplyDto);
    }


    @PostMapping(value = "approve")
    ResponseData approve(@RequestBody AskForLeaveApproveBean bean, HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.approve(bean,supplyDto);
    }

    @PostMapping(value = "history")
    ResponseData history(@RequestBody AskForLeaveHistoryBean bean , HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.history(bean,supplyDto);
    }



    @PostMapping(value = "delete/one")
    ResponseData deleteOne(@RequestBody WorkFlowDeleteBean bean, HttpServletRequest request){
        SupplyDto supplyDto = new SupplyDto().setLang(request.getHeader("lang"))
                .setUserName(request.getHeader("user_name"));
        return workFlowService.delete(bean.getId(),supplyDto);
    }



}
