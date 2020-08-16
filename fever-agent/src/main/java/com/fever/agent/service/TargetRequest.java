package com.fever.agent.service;

import com.fever.agent.config.RabbitConfiguration;
import com.fever.agent.model.TargetServerResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TargetRequest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Async
    public void targetRequest(Integer key) throws InterruptedException {
        TargetServerResponse targetServerResponse = new TargetServerResponse();
        targetServerResponse.setKey(key);
        targetServerResponse.setStartTime(System.currentTimeMillis());
        Thread.sleep(3000);
        targetServerResponse.setEndTime(System.currentTimeMillis());
        targetServerResponse.setResponseTime();
        rabbitTemplate.convertAndSend(RabbitConfiguration.FEVER_TPS_EXCHANGE, "fever.agent.test", targetServerResponse);
    }
}
