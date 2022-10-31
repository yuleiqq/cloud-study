package com.cognizant.workflow.askForLeave.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SupplyDto {
    private String userName;
    private String lang;
}
