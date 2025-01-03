package com.HireAtE.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HireAtE.Models.CompanyOnboardingEntity;
import com.HireAtE.Service.CompanyOnboardingService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyOnboardingService companyService;

    public CompanyController(CompanyOnboardingService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyOnboardingEntity> registerCompany(@RequestBody CompanyOnboardingEntity company) {
        CompanyOnboardingEntity registeredCompany = companyService.registerCompany(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }
    @GetMapping("/afnan")
    public String abc(){
        return "AFnan";
    }
}