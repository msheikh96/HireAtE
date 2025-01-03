package com.HireAtE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HireAtE.Models.IndividualOnboardingEntity;

@Repository
public interface IndividualOnboardingRepository extends JpaRepository<IndividualOnboardingEntity, Long> {
    // Define the custom query method to check if a CNIC exists
    boolean existsByCnic(String cnic);
}
