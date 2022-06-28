package com.tereshin.pp314.controllers;

import com.tereshin.pp314.models.User;
import com.tereshin.pp314.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authenticatedUser")
    public User authenticatedUser(Principal principal) {
        return userService.getUser(principal.getName());
    }
}
