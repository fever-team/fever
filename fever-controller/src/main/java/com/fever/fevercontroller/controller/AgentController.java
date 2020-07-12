package com.fever.fevercontroller.controller;

import com.fever.fevercontroller.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    private final SimpMessagingTemplate template;

    @Autowired
    public AgentController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @GetMapping("/start")
    public String execute() {
        template.convertAndSend("/subscribe/chat/room/1", "Start");
        return "test";
    }

    @GetMapping("/stop")
    public String stop() {
        template.convertAndSend("/subscribe/chat/room/1", "Stop");
        return "test";
    }

    @GetMapping("/progress")
    public String progress() {
        template.convertAndSend("/subscribe/chat/room/1", "Progress");
        return "test";
    }

    @GetMapping("/idle")
    public String idle() {
        template.convertAndSend("/subscribe/chat/room/1", "Idle");
        return "test";
    }

    @MessageMapping("/chat/join")
    public void join(ChatMessage message) {
        System.out.println("입장!!");
        message.setMessage(message.getWriter() + "님이 입장하셨습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }
}
