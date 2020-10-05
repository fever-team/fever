package com.fever.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup() {
        return new ResponseEntity<>("todo", HttpStatus.CREATED);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<String> signin() {
        return new ResponseEntity<>("todo", HttpStatus.OK);
    }
}
