package com.fever.fevercontroller.controller;

import com.fever.fevercontroller.model.RunRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AgentControllerController {

    @PostMapping(value = "/run")
    public void runAgent(@RequestBody RunRequest runRequest) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8081/run", runRequest, String.class);
    }
}
