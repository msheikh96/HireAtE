package com.HireAtE.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HireAtE.Models.IndividualOnboardingEntity;
import com.HireAtE.Repository.IndividualOnboardingRepository;
import com.HireAtE.Response.APIResponseClass;


@Service
public class IndividualOnboardingService {
     @Autowired
    private IndividualOnboardingRepository onboardingRepository;

    
    // Create a new onboarding record
    public APIResponseClass createOnboarding(IndividualOnboardingEntity onboarding) {

         // Custom validation example: check if the CNIC already exists
         if (onboardingRepository.existsByCnic(onboarding.getCnic())) {
            return new APIResponseClass("A record with the same CNIC already exists.", "10", null);
        }

        // Custom validation example: check if CGPA is within a valid range
        if (onboarding.getCgpa() < 0 || onboarding.getCgpa() > 4.0) {
            return new APIResponseClass("CGPA must be between 0.0 and 4.0.", "10", null);
        }
         onboardingRepository.save(onboarding);
        return new APIResponseClass("Onboarding record created successfully!", "00", null);

    }

    // Get all onboarding records
    public List<IndividualOnboardingEntity> getAllOnboardings() {
        return onboardingRepository.findAll();
    }

   
}
