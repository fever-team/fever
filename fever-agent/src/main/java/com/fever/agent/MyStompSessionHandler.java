package com.fever.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fever.agent.restapi.RestTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MyStompSessionHandler implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/greetings", this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        RestTemplate rt = new RestTemplate();
        String result = "";

        try {
            result = rt.test_controller();
        } catch (JsonProcessingException e) {

        }

        session.send("/app/controller", result);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return null;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {

    }
}
