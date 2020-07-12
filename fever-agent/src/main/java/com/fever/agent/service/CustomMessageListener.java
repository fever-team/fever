package com.fever.agent.service;

import com.fever.agent.model.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@Service
public class CustomMessageListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);

    @RabbitListener(queues = "q.fever")
    public void receiveMessage(final CustomMessage message) throws InterruptedException {
        log.info("" + message);
        System.out.println(message);

        WebClient webClient = WebClient.builder().build();
        Map<Mono<String>, Integer> mapping = new HashMap<>();

        for (int i = 0; i < message.getUserCount(); i++) {
            Mono<String> mono = webClient.get().uri(message.getText()).retrieve().bodyToMono(String.class);
            mapping.put(mono, i);

            System.out.print(i + " ");
            System.out.println(System.currentTimeMillis());
            mono.subscribe(response -> {
                System.out.println(mapping.get(mono)+ " " + response + " " + System.currentTimeMillis());
            });
        }
    }
}
