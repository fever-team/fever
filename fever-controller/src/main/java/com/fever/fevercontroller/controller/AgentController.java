package com.fever.fevercontroller.controller;

import com.fever.fevercontroller.model.AgentRequest;
import com.fever.fevercontroller.model.ControllerRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AgentController {

    @MessageMapping("/controller")
    public void controllerRequest(AgentRequest agentRequest) {
        System.out.println("Success connection!");
    }
}
