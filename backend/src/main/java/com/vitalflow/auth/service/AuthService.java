package com.vitalflow.auth.service;

import com.vitalflow.auth.dto.*;
import com.vitalflow.auth.entity.User;
import com.vitalflow.auth.enums.ProfileStatus;
import com.vitalflow.auth.repository.UserRepository;
import com.vitalflow.config.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository; this.passwordEncoder = passwordEncoder; this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (request.email() == null && request.phoneNumber() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or phone number is required");
        if (request.email() != null && userRepository.existsByEmailIgnoreCase(request.email())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        if (request.phoneNumber() != null && userRepository.existsByPhoneNumber(request.phoneNumber())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number is already registered");
        if (request.role().name().equals("ADMIN")) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin registration is not allowed");
        User user = new User(); user.setFullName(request.fullName()); user.setEmail(request.email()); user.setPhoneNumber(request.phoneNumber());
        user.setPassword(passwordEncoder.encode(request.password())); user.setRole(request.role()); user.setProfileStatus(ProfileStatus.PROFILE_INCOMPLETE);
        User saved = userRepository.save(user);
        return toResponse(saved, jwtService.generateToken(saved));
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmailIgnoreCase(request.identifier()).or(() -> userRepository.findByPhoneNumber(request.identifier()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        if (user.getProfileStatus() == ProfileStatus.SUSPENDED) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is suspended");
        return toResponse(user, jwtService.generateToken(user));
    }

    private AuthResponse toResponse(User user, String token) { return new AuthResponse(token, user.getId(), user.getFullName(), user.getRole(), user.getProfileStatus()); }
}
