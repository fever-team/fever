package com.fever.fevercontroller.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AgentResultResponse {
    private Integer totalVirtualUser;
    private Double avgTPS;
    private Integer peekTPS;
    private Integer executeTestCount;
    private Integer successTestCount;
    private Integer errorTestCount;
}
