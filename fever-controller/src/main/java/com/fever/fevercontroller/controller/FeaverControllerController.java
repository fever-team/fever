package com.fever.fevercontroller.controller;

import com.fever.fevercontroller.configuration.RabbitConfiguration;
import com.fever.fevercontroller.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeaverControllerController {

    private static final String topicExchange = "x.fever";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/start")
    public String start() {
        CustomMessage message = new CustomMessage("http://localhost:5000/v1/posts", 30, 3);
        rabbitTemplate.convertAndSend(RabbitConfiguration.FEVER_TPS_EXCHANGE, "fever.agent.test", message);
        return "TEST";
    }

}
