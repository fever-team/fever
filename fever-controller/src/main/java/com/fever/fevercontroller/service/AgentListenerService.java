package com.fever.fevercontroller.service;

import com.fever.fevercontroller.model.AgentResultResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AgentListenerService {

    @RabbitListener(queues = "fever-agent-tps-queue")
    public void receiveMessage(String result) {
        System.out.println(result);
    }

    @RabbitListener(queues = "fever-agent-result-queue")
    public void resultMessage(AgentResultResponse agentResultResponse) {
        System.out.println(agentResultResponse.toString());
    }
}
