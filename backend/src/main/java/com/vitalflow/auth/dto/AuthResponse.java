package com.vitalflow.auth.dto;

import com.vitalflow.auth.enums.ProfileStatus;
import com.vitalflow.auth.enums.Role;

public record AuthResponse(String token, Long userId, String fullName, Role role, ProfileStatus profileStatus) { }
