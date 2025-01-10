package com.HireAtE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HireAtE.Models.IndividualOnboardingEntity;
import com.HireAtE.Response.APIResponseClass;
import com.HireAtE.Service.IndividualOnboardingService;

@RestController
@RequestMapping("/api/indv")
public class IndividualOnboardingController {
    
    @Autowired
    private final IndividualOnboardingService individualOnboardingservice;
    
    public IndividualOnboardingController(IndividualOnboardingService individualOnboardingservice) {
        this.individualOnboardingservice = individualOnboardingservice;
        
    }


     @PostMapping("/IndividualOnboarding")
    public ResponseEntity<APIResponseClass> registerCompany(@RequestBody IndividualOnboardingEntity company) {
        APIResponseClass registeredCompany = individualOnboardingservice.createOnboarding(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }

   

    @PostMapping("/UserLogin")
    public ResponseEntity<APIResponseClass> UserLogin(@RequestBody IndividualOnboardingEntity indv) {
        APIResponseClass registeredCompany = individualOnboardingservice.UserLogin(indv.getEmail(),indv.getPassword());
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }


}
