package com.fever.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FeverAgent {

    public static void main(String[] args) {
        SpringApplication.run(FeverAgent.class, args);
    }
}
