package com.bloodysweet.vitalflow.donor.dto;

public class DonorResponse {

    private final Long id;
    private final String message;

    public DonorResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
