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
        return companyRepository.save(company);
    }

   
}
