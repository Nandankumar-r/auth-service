package com.nandan.auth.service;

import com.nandan.auth.dto.LoginRequest;
import com.nandan.auth.dto.RegisterRequest;
import com.nandan.auth.entity.Role;
import com.nandan.auth.entity.User;
import com.nandan.auth.exception.InvalidRoleException;
import com.nandan.auth.repository.UserRepository;
import com.nandan.auth.strategy.AuthStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthStrategy authStrategy;

    public AuthService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            @Qualifier("jwtAuthStrategy") AuthStrategy authStrategy
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authStrategy = authStrategy;
    }

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already taken.";
        }
        Role roleEnum;
        try {
            roleEnum = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidRoleException("Invalid role: " + request.getRole());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);
        user.setRole(roleEnum);

        userRepository.save(user);
        return "User registered successfully.";
    }

    public ResponseEntity<?> login(LoginRequest request) {
        try {
            String token = authStrategy.authenticateAndIssueToken(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(Map.of("accessToken", token));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
