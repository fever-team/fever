package com.fever.agent.service;

import com.fever.agent.model.RunRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    TargetRequestService targetRequestService;

    public void run(RunRequest runRequest, AgentManager agentManager) {
        for (int virtualUserCnt = 0; virtualUserCnt < runRequest.getTotalUser(); virtualUserCnt++) {
            targetRequestService.targetRequest(runRequest, agentManager);
        }
    }
}
