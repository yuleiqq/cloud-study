package com.cognizant.workflow.askForLeave.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author franky
 * @since 2022-07-05
 */
@Data
public class AskForLeaveStartBean {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    Date startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    Date endDate;
    String reason;
    String approver;
    String applier;
}
