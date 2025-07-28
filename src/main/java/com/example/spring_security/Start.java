package com.example.spring_security;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Start {

    @GetMapping("/")
    public String start(){
        return "Application Started";
    }
}
