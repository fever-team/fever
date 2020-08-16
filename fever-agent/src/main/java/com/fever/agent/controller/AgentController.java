package com.fever.agent.controller;

import com.fever.agent.config.RabbitConfiguration;
import com.fever.agent.model.ExecuteRequest;
import com.fever.agent.service.TargetRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TargetRequest targetRequest;

    private Integer count = 0;

    @Async
    @PostMapping(value = "/execute")
    public void execute(@RequestBody ExecuteRequest executeRequest) throws Exception {
        count++;
        executeRequest.setKey(count);
        for (int i = 0; i < executeRequest.getTotalUser(); i++) {
            targetRequest.targetRequest(executeRequest.getKey());
            Thread.sleep(executeRequest.getInterval());
        }
    }
}