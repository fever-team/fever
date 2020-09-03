package com.fever.agent.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AgentResult {
    private Integer totalVirtualUser;
    private Double avgTPS;
    private Integer peekTPS;
    private Integer executeTestCount;
    private Integer successTsetCount;
    private Integer errorTestCount;
}
