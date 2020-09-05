package com.fever.fevercontroller.model;

import lombok.Data;

@Data
public class ExecuteRequest {
    private Integer key;
    private String url;
    private Integer interval;
    private Integer totalUser;
}
