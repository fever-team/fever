package com.fever.agent.service;

import com.fever.agent.model.ExecuteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    TargetRequestService targetRequestService;

    public void run(ExecuteRequest executeRequest, AgentManager agentManager) {
        for (int virtualUserCnt = 0; virtualUserCnt < executeRequest.getTotalUser(); virtualUserCnt++) {
            targetRequestService.targetRequest(executeRequest, agentManager);
        }
    }
}
