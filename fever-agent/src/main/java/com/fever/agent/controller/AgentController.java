package com.fever.agent.controller;

import com.fever.agent.model.ExecuteRequest;
import com.fever.agent.service.AgentManager;
import com.fever.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    @Autowired
    AgentService agentService;

    @Autowired
    AgentManager agentManager;

    @PostMapping(value = "/run")
    public void run(@RequestBody ExecuteRequest executeRequest) throws Exception {
        agentManager.start();
        agentManager.setTotalVirtualUser(executeRequest.getTotalUser());
        agentService.run(executeRequest, agentManager);
    }
}