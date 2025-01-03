package com.HireAtE.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HireAtE.Models.IndividualOnboardingEntity;
import com.HireAtE.Service.IndividualOnboardingService;

@RestController
@RequestMapping("/api/onboarding")
public class IndividualOnboardingController {
    
    private final IndividualOnboardingService individualOnboardingservice;

    public IndividualOnboardingController(IndividualOnboardingService individualOnboardingservice) {
        this.individualOnboardingservice = individualOnboardingservice;
    }


     @PostMapping("/IndividualOnboarding")
    public ResponseEntity<IndividualOnboardingEntity> registerCompany(@RequestBody IndividualOnboardingEntity company) {
        IndividualOnboardingEntity registeredCompany = individualOnboardingservice.createOnboarding(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }

    // Get all individual onboarding records
    @GetMapping
    public List<IndividualOnboardingEntity> getAllOnboardings() {
        // Logic to retrieve all onboarding records from the database
        return List.of(); // Return a dummy empty list for now
    }

}
