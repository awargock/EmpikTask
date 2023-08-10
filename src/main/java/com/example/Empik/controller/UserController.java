package com.example.Empik.controller;

import com.example.Empik.model.User;
import com.example.Empik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{login}")
    public User getUser(@PathVariable String login) {
        return userService.getUserInfo(login);
    }
}

