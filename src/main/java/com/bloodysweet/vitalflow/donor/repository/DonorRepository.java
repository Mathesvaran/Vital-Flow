package com.bloodysweet.vitalflow.donor.repository;

import com.bloodysweet.vitalflow.donor.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
