package com.example.spring_security.service;

import com.example.spring_security.Entity.Role;
import com.example.spring_security.Entity.User;
import com.example.spring_security.Repository.RoleRepository;
import com.example.spring_security.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public User saveUser() {
        // Find role by name or id
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Hash password
        String encodedPassword = passwordEncoder.encode("aleem");

        // Build user
        User user = new User();
        user.setUsername("aleem");
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(userRole));  // add role

        return userRepository.save(user);
    }


    public User saveAdmin() {
        // Find role by name or id
        Role userRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Hash password
        String encodedPassword = passwordEncoder.encode("admin");

        // Build user
        User user = new User();
        user.setUsername("admin");
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(userRole));  // add role

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }


}
