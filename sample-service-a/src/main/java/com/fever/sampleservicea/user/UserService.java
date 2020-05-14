package com.fever.sampleservicea.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
