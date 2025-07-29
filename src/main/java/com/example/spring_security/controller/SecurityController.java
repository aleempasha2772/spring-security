package com.example.spring_security.controller;


import com.example.spring_security.Entity.User;
import com.example.spring_security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
    @PostMapping("/create")
    public ResponseEntity<User> createUser() {
        User savedUser = customUserDetailsService.saveUser();
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/create/admin")
    public ResponseEntity<User> createAdminUser() {
        User savedUser = customUserDetailsService.saveAdmin();
        return ResponseEntity.ok(savedUser);
    }

    /*
    INSERT INTO roles (name) VALUES ('ROLE_USER');
    INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

     */


}
