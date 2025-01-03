package com.HireAtE.Service;

import org.springframework.stereotype.Service;

import com.HireAtE.Models.CompanyOnboardingEntity;
import com.HireAtE.Repository.CompanyOnboardingRepository;

@Service
public class CompanyOnboardingService {

    private final CompanyOnboardingRepository companyRepository;

   
    public CompanyOnboardingService(CompanyOnboardingRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyOnboardingEntity registerCompany(CompanyOnboardingEntity company) {
        if (companyRepository.existsBycompanyName(company.getCompanyName())) {
            throw new IllegalArgumentException("A record with the same Company name already exists.");
        }
        return companyRepository.save(company);
    }

   
}
