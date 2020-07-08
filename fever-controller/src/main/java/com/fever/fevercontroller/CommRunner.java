package com.fever.fevercontroller;

import com.fever.fevercontroller.model.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommRunner implements CommandLineRunner {

    private static final String topicExchange = "x.fever";

    private final RabbitTemplate rabbitTemplate;

    public CommRunner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final Logger log = LoggerFactory.getLogger(CommRunner.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending Message....");
        log.info("Sending Message....");
        CustomMessage message = new CustomMessage("Hello Message!", 1, true);
        rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", message);
    }
}
