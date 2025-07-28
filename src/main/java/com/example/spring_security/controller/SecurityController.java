package com.example.spring_security.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @GetMapping("greeting")
    public String greeting(Authentication authentication) {
        String userName = authentication.getName();

        return "Spring Security In-memory Authentication Example - Welcome " + userName;
    }

    @GetMapping("/user/greeting")
    public String userGreeting(Authentication authentication) {
        String userName = authentication.getName();
        return "Hello you are a user : " + userName;
    }
    @GetMapping("/admin/greeting")
    public String adminGreeting(Authentication authentication) {
        String userName = authentication.getName();
        return "Hello you are admin : " + userName;
    }



    @GetMapping("/public/greeting")
    public String publicGreeting() {
        return "Spring Security In-memory Authentication Example - Public Greeting";
    }
}
