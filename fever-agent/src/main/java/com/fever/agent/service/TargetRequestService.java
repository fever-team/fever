package com.fever.agent.service;

import com.fever.agent.model.RunRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TargetRequestService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Async
    public void targetRequest(RunRequest runRequest, AgentManager agentManager) {
        RestTemplate restTemplate = new RestTemplate();

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60000) {
            agentManager.increaseUserCount();
            agentManager.increaseTestCount();

            try {
                restTemplate.getForEntity(runRequest.getUrl(), String.class);
                agentManager.increaseSuccessCount();
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                    agentManager.increaseSuccessCount();
                } else {
                    agentManager.increaseErrorCount();
                }
            }
        }
        agentManager.setIsRun(false);
    }
}
