package com.vitalflow.auth.dto;

import com.vitalflow.auth.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank String fullName,
        String email,
        String phoneNumber,
        @NotBlank String password,
        @NotNull Role role) { }
