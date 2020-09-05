package com.fever.fevercontroller.controller;

import com.fever.fevercontroller.model.ExecuteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AgentControllerController {

    @PostMapping(value = "/run")
    public void runAgent(@RequestBody ExecuteRequest executeRequest) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8081/run", executeRequest, String.class);
    }
}
