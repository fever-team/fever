package com.fever.agent.model;

import lombok.Data;

@Data
public class RunRequest {
    private Integer key;
    private String url;
    private Integer interval;
    private Integer totalUser;
}
