package com.HireAtE.Service;

import com.HireAtE.Models.JobApplicationEntity;
import com.HireAtE.Repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    // Get all job applications for a user by email
    public List<JobApplicationEntity> getApplicationsByEmail(String email) {
        return jobApplicationRepository.findByIndividual_Email(email); // Corrected method call
    }

    // Apply for a job
    public JobApplicationEntity applyForJob(JobApplicationEntity jobApplicationEntity) {
        return jobApplicationRepository.save(jobApplicationEntity); // Save job application
    }

    // Get job applications by individual ID and application status
    public List<JobApplicationEntity> getApplicationsByStatus(Long individualId,
            JobApplicationEntity.ApplicationStatus status) {
        return jobApplicationRepository.findByIndividualIdAndApplicationStatus(individualId, status);
    }

    // Get job applications by category, city, or company name
    public List<JobApplicationEntity> getApplicationsByJobCategory(String category) {
        return jobApplicationRepository.findByJobCategory(category);
    }

    public List<JobApplicationEntity> getApplicationsByJobCity(String city) {
        return jobApplicationRepository.findByJobCity(city);
    }

    public List<JobApplicationEntity> getApplicationsByJobCompany(String companyName) {
        return jobApplicationRepository.findByJobCompanyName(companyName);
    }

    // Fetch job application details by ID
    public Optional<JobApplicationEntity> getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
    }
}
