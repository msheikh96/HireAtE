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

        // Check if CNIC is not null or empty
    if (onboarding.getCnic() == null || onboarding.getCnic().trim().isEmpty()) {
        return new APIResponseClass("CNIC cannot be empty.", "10");
    }

    // Custom validation example: check if the CNIC already exists
    if (onboardingRepository.existsByCnic(onboarding.getCnic())) {
        return new APIResponseClass("A record with the same CNIC already exists.", "10");
    }

    // Check if name is not null or empty
    if (onboarding.getFirstName() == null || onboarding.getFirstName().trim().isEmpty()) {
        return new APIResponseClass("First Name cannot be empty.", "10");
    }
    if (onboarding.getLastName() == null || onboarding.getLastName().trim().isEmpty()) {
        return new APIResponseClass("Last Name cannot be empty.", "10");
    }

    // Check if email is not null or empty
    if (onboarding.getEmail() == null || onboarding.getEmail().trim().isEmpty()) {
        return new APIResponseClass("Email cannot be empty.", "10");
    }

    // Check if the date of birth (if applicable) is valid (example validation)
    if (onboarding.getDob() == null) {
        return new APIResponseClass("Date of birth cannot be empty.", "10");
    }

    // Example: If there is a phone number, check if it's valid (optional)
    if (onboarding.getPhoneNo() != null && !isValidPhoneNumber(onboarding.getPhoneNo())) {
        return new APIResponseClass("Invalid phone number format.", "10");
    }

    // Validate email format using a simple regular expression
    if (!isValidEmail(onboarding.getEmail())) {
        return new APIResponseClass("Invalid email format.", "10");
    }
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

    public APIResponseClass UserLogin(String email,String password)
    {

         // Validate email and password are not null or empty
    if (email == null || email.trim().isEmpty()) {
        return new APIResponseClass("Email cannot be empty.", "10");
    }

    if (password == null || password.trim().isEmpty()) {
        return new APIResponseClass("Password cannot be empty.", "10");
    }

    // Validate email format using a simple regular expression
    if (!isValidEmail(email)) {
        return new APIResponseClass("Invalid email format.", "10");
    }

    // Validate password length (adjust length as per your requirement)
    if (password.length() < 6) {
        return new APIResponseClass("Password must be at least 6 characters long.", "10");
    }
        IndividualOnboardingEntity user = onboardingRepository.findByEmail(email);

        if(user==null)
        {
            return new APIResponseClass("User not found.", "10");

        }

        if(password!=user.getPassword())
        {

            return new APIResponseClass("Incorrect Password", "10");

        }

        return new APIResponseClass("Login successful.", "00");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";  // Basic regex for email validation
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^[0-9]{11}$";  // Simple phone number validation (10 digits)
        return phone.matches(phoneRegex);
    }
}
