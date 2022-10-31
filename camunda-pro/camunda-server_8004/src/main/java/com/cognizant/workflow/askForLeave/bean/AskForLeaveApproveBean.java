package com.cognizant.workflow.askForLeave.bean;

import lombok.Data;

@Data
/***
 *
 * 暂时设置bean和entity的属性一样，之后代码生成的时候再处理
 */
public class AskForLeaveApproveBean {
    String taskId;
    String comment;
    String approver;
}
