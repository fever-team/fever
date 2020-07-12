package com.fever.agent.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplate {

//    @GetMapping("/target_status")
    public String test_controller() throws JsonProcessingException {
        HashMap<String, Object> result = new HashMap<String, Object>();

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();

        ResponseEntity<Map> resultMap = restTemplate.exchange("http://www.github.com/qlcid", HttpMethod.GET, entity, Map.class);
        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인

        System.out.println(result);
        return result.toString();
    }
}
