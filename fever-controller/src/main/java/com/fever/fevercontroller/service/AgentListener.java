package com.fever.fevercontroller.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fever.fevercontroller.model.TargetServerResponse;

@Service
public class AgentListener {

    @RabbitListener(queues = "my-queue")
    public void receiveMessage(TargetServerResponse targetServerResponse) {
        System.out.println(targetServerResponse.toString());
    }
}
