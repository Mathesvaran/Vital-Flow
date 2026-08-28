package com.bloodysweet.vitalflow.donor.service;

import com.bloodysweet.vitalflow.auth.entity.Role;
import com.bloodysweet.vitalflow.auth.entity.User;
import com.bloodysweet.vitalflow.auth.repository.UserRepository;
import com.bloodysweet.vitalflow.donor.dto.DonorRequest;
import com.bloodysweet.vitalflow.donor.dto.DonorResponse;
import com.bloodysweet.vitalflow.donor.entity.Donor;
import com.bloodysweet.vitalflow.donor.repository.DonorRepository;
import org.springframework.stereotype.Service;

@Service
public class DonorService {

    private final DonorRepository donorRepository;
    private final UserRepository userRepository;

    public DonorService(DonorRepository donorRepository, UserRepository userRepository) {
        this.donorRepository = donorRepository;
        this.userRepository = userRepository;
    }

    public DonorResponse createProfile(Long userId, DonorRequest request) {
        if (donorRepository.existsById(userId)) {
            throw new IllegalArgumentException("Donor profile already exists for this user");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRole() != Role.DONOR) {
            throw new IllegalArgumentException("Only donor users can create donor profiles");
        }

        Donor donor = new Donor();
        donor.setUser(user);
        donor.setFullName(request.getFullName());
        donor.setAge(request.getAge());
        donor.setPhoneNumber(request.getPhoneNumber());
        donor.setBloodGroup(request.getBloodGroup());
        donor.setWeight(request.getWeight());
        donor.setLastDonationDate(request.getLastDonationDate());
        donor.setHasChronicCondition(request.getHasChronicCondition());
        donor.setGovernmentId(request.getGovernmentId());
        donor.setCity(request.getCity());
        donor.setPostalCode(request.getPostalCode());

        Donor saved = donorRepository.save(donor);
        return new DonorResponse(saved.getId(), "Donor profile created");
    }
}
