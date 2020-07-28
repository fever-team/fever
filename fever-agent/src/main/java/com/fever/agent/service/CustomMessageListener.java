package com.fever.agent.service;

import com.fever.agent.model.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Service
public class CustomMessageListener {

    @Autowired
    WebClient.Builder builder;

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);
    private Map<ClientResponse, Long> mapping = new HashMap<>();

    @RabbitListener(queues = "fever-tps-queue")
    public void receiveMessage2(final CustomMessage message) {

        WebClient webClient = builder.build();

        Mono<String> helloMono = webClient.get().uri(message.getRequestURL())
                .retrieve()
                .bodyToMono(String.class);

        for(int i = 0; i < 10; i++) {
            helloMono.subscribe(s-> {
                System.out.println("TEST");
            });
        }
    }


    @RabbitListener(queues = "q.fever")
    public void receiveMessage(final CustomMessage message) throws InterruptedException {
        log.info("" + message);

        WebClient webClient = WebClient.builder().baseUrl(message.getRequestURL())
                .filter(logRequest()).build();

        Flux.interval(Duration.ofMillis(message.getInterval()))
                .take(message.getTotalSecond() * 1000 / message.getInterval())
                .flatMap((var id) -> webClient.get()
                        .uri(message.getRequestURL())
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
                        .flatMap((var response) -> {
                            if (response.statusCode().isError()) {
                                return Mono.error(new WebClientResponseException(
                                        response.statusCode().value(), response.statusCode().getReasonPhrase()
                                        , response.headers().asHttpHeaders(), null, null));
                            }
                            return response.bodyToMono(String.class);
                        }).onErrorMap(e -> {
                            log.error(e.getMessage());
                            return e;
                        })
                        .elapsed()
                        .doOnNext(tuple -> log.info("{}th Request Milliseconds for retrieving {}", id, tuple.getT1()))
                        .map(Tuple2::getT2)).subscribe();

    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response: {}", clientResponse.headers().asHttpHeaders().get("property-header"));
            return Mono.just(clientResponse);
        });
    }
}
