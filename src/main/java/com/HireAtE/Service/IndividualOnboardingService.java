package com.HireAtE.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HireAtE.Models.IndividualOnboardingEntity;
import com.HireAtE.Repository.IndividualOnboardingRepository;


@Service
public class IndividualOnboardingService {
     @Autowired
    private IndividualOnboardingRepository onboardingRepository;

    
    // Create a new onboarding record
    public IndividualOnboardingEntity createOnboarding(IndividualOnboardingEntity onboarding) {

         // Custom validation example: check if the CNIC already exists
         if (onboardingRepository.existsByCnic(onboarding.getCnic())) {
            throw new IllegalArgumentException("A record with the same CNIC already exists.");
        }

        // Custom validation example: check if CGPA is within a valid range
        if (onboarding.getCgpa() < 0 || onboarding.getCgpa() > 4.0) {
            throw new IllegalArgumentException("CGPA must be between 0.0 and 4.0.");
        }
        return onboardingRepository.save(onboarding);
    }

    // Get all onboarding records
    public List<IndividualOnboardingEntity> getAllOnboardings() {
        return onboardingRepository.findAll();
    }

   
}
