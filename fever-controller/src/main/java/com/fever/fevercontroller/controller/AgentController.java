package com.fever.fevercontroller.controller;

import com.fever.fevercontroller.model.AgentRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AgentController {

    @MessageMapping("/controller")
    @SendTo("/topic/greetings")
    public AgentRequest controllerRequest(@Payload AgentRequest agentRequest) {
        System.out.println(agentRequest.getContent());

        return agentRequest;
    }
}
