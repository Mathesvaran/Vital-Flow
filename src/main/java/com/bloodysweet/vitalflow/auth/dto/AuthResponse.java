package com.bloodysweet.vitalflow.auth.dto;

public class AuthResponse {

    private boolean success;
    private String message;
    private Long userId;

    public AuthResponse(boolean success, String message, Long userId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }
}
