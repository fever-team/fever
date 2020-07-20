package com.fever.agent.service;

import com.fever.agent.model.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);

    private Map<ClientResponse, Long> mapping = new HashMap<>();

    @RabbitListener(queues = "q.fever")
    public void receiveMessage(final CustomMessage message) throws InterruptedException {
        log.info("" + message);
//        System.out.println(message);

        WebClient webClient = WebClient.builder().baseUrl(message.getRequestURL())
                .filter(logRequest()).build();
//
//
//        Flux.interval(Duration.ofMillis(message.getInterval()))
//                .take(message.getTotalSecond() * 1000 / message.getInterval())
//                .flatMap((var id) -> webClient.get()
//                        .uri(message.getRequestURL())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .exchange()
//                        .flatMap((var response) -> {
//                            if (response.statusCode().isError()) {
//                                return Mono.error(new WebClientResponseException(
//                                        response.statusCode().value(), response.statusCode().getReasonPhrase()
//                                        , response.headers().asHttpHeaders(), null, null));
//                            }
//                            return response.bodyToMono(String.class);
//                        }).onErrorMap(e -> {
//                            log.error(e.getMessage());
//                            return e;
//                        })).subscribeOn(Schedulers.parallel()).subscribe((var response) -> {
//            System.out.println(response + " " + System.currentTimeMillis());
//        });

//
//        for (int i = 0; i < message.getUserCount(); i++) {
//            Mono<String> mono = webClient.get().uri(message.getText())
//                    .accept(MediaType.APPLICATION_JSON).exchange()
//                    .flatMap((var response) -> {
//                        if (response.statusCode().isError()) {
//                            return Mono.error(new WebClientResponseException(
//                                    response.statusCode().value(), response.statusCode().getReasonPhrase()
//                                    , response.headers().asHttpHeaders(), null, null));
//                        }
//                        return response.bodyToMono(String.class);
//                    }).onErrorMap(e -> {
//                        log.error(e.getMessage());
//                        return e;
//                    });
//
//            mapping.put(mono, i);
//
//            System.out.print(i + " ");
//            System.out.println(System.currentTimeMillis());
//            mono.subscribe(response -> {
//                System.out.println(mapping.get(mono) + " " + response + " " + System.currentTimeMillis());
//            });
//        }

//        ClientResponse clientResponse = webClient
//                .get().uri(uriBuilder -> uriBuilder.path(message.getRequestURL())
////                        .queryParam("param", "value")
//                        .build())
//                .exchange()
//                .block();
//        log.info("Response: {}", clientResponse.toEntity(String.class).block());


//        WebClient webClient2 = WebClient.builder().baseUrl("your_url")
//                .filter(logRequest())
//                .filter(logResponse())
//                .build();

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
//            clientRequest.headers()
//                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
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
