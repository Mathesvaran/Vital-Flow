package com.bloodysweet.vitalflow.auth.service;

import com.bloodysweet.vitalflow.auth.dto.AuthResponse;
import com.bloodysweet.vitalflow.auth.dto.LoginRequest;
import com.bloodysweet.vitalflow.auth.dto.RegisterRequest;
import com.bloodysweet.vitalflow.auth.entity.User;
import com.bloodysweet.vitalflow.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User saved = userRepository.save(user);
        return new AuthResponse(true, "Registration successful", saved.getId());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return new AuthResponse(true, "Login successful", user.getId());
    }
}
