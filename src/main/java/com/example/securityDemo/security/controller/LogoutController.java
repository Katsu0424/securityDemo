package com.example.securityDemo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

    @GetMapping
    public String index(){
        return "logout";
    }
}
