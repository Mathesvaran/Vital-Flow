package com.bloodysweet.vitalflow.donor.controller;

import com.bloodysweet.vitalflow.donor.dto.DonorRequest;
import com.bloodysweet.vitalflow.donor.dto.DonorResponse;
import com.bloodysweet.vitalflow.donor.service.DonorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/{userId}/profile")
    public ResponseEntity<DonorResponse> createProfile(@PathVariable Long userId, @Valid @RequestBody DonorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donorService.createProfile(userId, request));
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<DonorResponse> handleBadRequest(Exception exception) {
        return ResponseEntity.badRequest().body(new DonorResponse(null, exception.getMessage()));
    }
}
