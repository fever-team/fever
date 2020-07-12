package com.fever.agent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class MyStompSessionHandler implements StompSessionHandler {

    // Connection 이루어지고 한번 동작
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/subscribe/chat/room/1", this);
        JSONObject obj1 = new JSONObject();
        obj1.put("chatRoomId", "1");
        obj1.put("writer", "adsfadf");
        obj1.put("message", "adsfads");
        session.send("/publish/chat/join", obj1);

    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        // TODO: Error Handler
        handleFrame(headers, new String(payload));
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return null;
    }

    // 서버로부터 응답을 받을 때 동작
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        String token = payload.toString();
        Agent agent = Agent.getInstance();
        System.out.println(token);
        if(token.equals("Idle")) {
            agent.state = AgentState.Idle;
        }else if(token.equals("Start")) {
            agent.state = AgentState.Start;
        }else if(token.equals("Stop")) {
            agent.state = AgentState.Stop;
        }else if(token.equals("Progress")) {
            agent.state = AgentState.Progress;
        }
    }
}
