package com.fever.agent.controller;

import com.fever.agent.model.RunRequest;
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

    @PostMapping(value = "/run")
    public void run(@RequestBody RunRequest runRequest) {
        AgentManager agentManager = new AgentManager();
        agentManager.start();
        agentManager.setTotalVirtualUser(runRequest.getTotalUser());
        agentService.run(runRequest, agentManager);
    }
}