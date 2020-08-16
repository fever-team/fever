package com.fever.agent.model;

import lombok.Data;

@Data
public class TargetServerResponse {
    private Integer key;
    private Long startTime;
    private Long endTime;
    private Long responseTime;

    public void setResponseTime() {
        this.responseTime = (this.endTime - this.startTime);
    }
}
