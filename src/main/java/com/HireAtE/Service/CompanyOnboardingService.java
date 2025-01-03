package com.HireAtE.Service;

import org.springframework.stereotype.Service;

import com.HireAtE.Models.CompanyOnboardingEntity;
import com.HireAtE.Repository.CompanyOnboardingRepository;
import com.HireAtE.Response.APIResponseClass;

@Service
public class CompanyOnboardingService {

    private final CompanyOnboardingRepository companyRepository;

   
    public CompanyOnboardingService(CompanyOnboardingRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public APIResponseClass registerCompany(CompanyOnboardingEntity company) {
        if (companyRepository.existsBycompanyName(company.getCompanyName())) {
            return new APIResponseClass("A record with the same Company name already exists.", "10");

        }
         companyRepository.save(company);
         return new APIResponseClass("Company Registered successfully!", "00");

    }

   
}
