package com.fever.agent.service;

import com.fever.agent.model.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class CustomMessageListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);

    @RabbitListener(queues = "q.fever")
    public void receiveMessage(final CustomMessage message) {
        log.info("" + message);
        System.out.println(message);
    }

}
