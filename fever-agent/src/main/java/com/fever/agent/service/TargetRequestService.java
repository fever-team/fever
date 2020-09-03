package com.fever.agent.service;

import com.fever.agent.model.ExecuteRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TargetRequestService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Async
    public void targetRequest(ExecuteRequest executeRequest, AgentManager agentManager) {
        WebClient webClient = webClientBuilder.baseUrl(executeRequest.getUrl()).build();

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60000) {
            agentManager.increaseUserCount();
            agentManager.increaseTestCount();
            webClient.get().uri("")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
    }
}
