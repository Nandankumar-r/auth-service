package com.nandan.auth.service;

import com.nandan.auth.dto.RegisterRequest;
import com.nandan.auth.entity.User;
import com.nandan.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already taken.";
        }

        userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build());
        return "User registered successfully.";
    }
}
