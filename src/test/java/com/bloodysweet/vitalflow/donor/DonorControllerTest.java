package com.bloodysweet.vitalflow.donor;

import com.bloodysweet.vitalflow.auth.dto.RegisterRequest;
import com.bloodysweet.vitalflow.auth.entity.Role;
import com.bloodysweet.vitalflow.auth.repository.UserRepository;
import com.bloodysweet.vitalflow.donor.dto.DonorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DonorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createDonorProfileShouldSucceedForDonorUser() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("profile@example.com");
        registerRequest.setPassword("secret123");
        registerRequest.setRole(Role.DONOR);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());

        Long userId = userRepository.findByEmail("profile@example.com").orElseThrow().getId();

        DonorRequest donorRequest = new DonorRequest();
        donorRequest.setFullName("Profile Donor");
        donorRequest.setAge(28);
        donorRequest.setPhoneNumber("1234567890");
        donorRequest.setBloodGroup("O+");
        donorRequest.setWeight(60.5);
        donorRequest.setLastDonationDate(LocalDate.of(2026, 1, 1));
        donorRequest.setHasChronicCondition(false);
        donorRequest.setGovernmentId("ID12345");
        donorRequest.setCity("Chennai");
        donorRequest.setPostalCode("600001");

        mockMvc.perform(post("/donors/{userId}/profile", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(donorRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.message").value("Donor profile created"));
    }

    @Test
    void createDonorProfileShouldFailForRecipientUser() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("recipient@example.com");
        registerRequest.setPassword("secret123");
        registerRequest.setRole(Role.RECIPIENT);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());

        Long userId = userRepository.findByEmail("recipient@example.com").orElseThrow().getId();

        DonorRequest donorRequest = new DonorRequest();
        donorRequest.setFullName("Recipient User");
        donorRequest.setAge(30);
        donorRequest.setPhoneNumber("1234567890");
        donorRequest.setBloodGroup("A+");
        donorRequest.setWeight(61.0);
        donorRequest.setHasChronicCondition(false);
        donorRequest.setGovernmentId("ID0001");
        donorRequest.setCity("Madurai");
        donorRequest.setPostalCode("625001");

        mockMvc.perform(post("/donors/{userId}/profile", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(donorRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Only donor users can create donor profiles"));
    }
}
