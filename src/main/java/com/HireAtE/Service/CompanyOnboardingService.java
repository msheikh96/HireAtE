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
            return new APIResponseClass("A record with the same Company name already exists.", "10", null);

        }

        if (companyRepository.existsByEmail(company.getEmail())) {
            return new APIResponseClass("A record with the same Email already exists.", "10", null);

        }

         companyRepository.save(company);
         return new APIResponseClass("Company Registered successfully!", "00", null);

    }

   public APIResponseClass UserLogin(String email,String password)
    {

         // Validate email and password are not null or empty
    if (email == null || email.trim().isEmpty()) {
        return new APIResponseClass("Email cannot be empty.", "10",null);
    }

    if (password == null || password.trim().isEmpty()) {
        return new APIResponseClass("Password cannot be empty.", "10",null);
    }

    // Validate email format using a simple regular expression
    if (!isValidEmail(email)) {
        return new APIResponseClass("Invalid email format.", "10",null);
    }

    // Validate password length (adjust length as per your requirement)
    if (password.length() < 6) {
        return new APIResponseClass("Password must be at least 6 characters long.", "10",null);
    }
        CompanyOnboardingEntity user = companyRepository.findByEmail(email);

        if(user==null)
        {
            return new APIResponseClass("User not found.", "10",null);

        }

        if(!user.getPassword().equals(password))
        {

            return new APIResponseClass("Incorrect Password", "10",null);

        }

        return new APIResponseClass("Login successful.", "00",null);
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";  // Basic regex for email validation
        return email.matches(emailRegex);
    }
}
