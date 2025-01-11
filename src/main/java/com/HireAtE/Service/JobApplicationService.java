package com.HireAtE.Service;

import com.HireAtE.Models.IndividualOnboardingEntity;
import com.HireAtE.Models.JobApplicationEntity;
import com.HireAtE.Models.JobEntity;
import com.HireAtE.Repository.JobApplicationRepository;
import com.HireAtE.Repository.IndividualOnboardingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final IndividualOnboardingRepository individualOnboardingRepository;
    private final JobService jobService;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
            IndividualOnboardingRepository individualOnboardingRepository,
            JobService jobService) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.individualOnboardingRepository = individualOnboardingRepository;
        this.jobService = jobService;
    }

    // Get all job applications for a user by email
    public List<JobApplicationEntity> getApplicationsByEmail(String email) {
        return jobApplicationRepository.findByIndividual_Email(email);
    }

    // Apply for a job
    public JobApplicationEntity applyForJob(Long individualId, Long jobId) {
        // Find individual by ID
        Optional<IndividualOnboardingEntity> individualOpt = individualOnboardingRepository.findById(individualId);
        if (individualOpt.isPresent()) {
            IndividualOnboardingEntity individual = individualOpt.get();

            // Create a new job application entity
            JobApplicationEntity jobApplication = new JobApplicationEntity();
            jobApplication.setIndividual(individual);

            // Get the job details using jobService and set the company
            Optional<JobEntity> jobOpt = jobService.getJobById(jobId);
            if (jobOpt.isPresent()) {
                JobEntity job = jobOpt.get();
                jobApplication.setJob(job);
                jobApplication.setCompany(job.getCompany()); // Set the company from the job entity
                // companyId will be automatically set by the setCompany method in
                // JobApplicationEntity
            } else {
                throw new RuntimeException("Job not found");
            }

            jobApplication.setApplicationStatus(JobApplicationEntity.ApplicationStatus.APPLIED);
            jobApplication.setApplicationMessage("Successfully applied for the job.");

            // Set CNIC from the individual
            jobApplication.setIndividualCnic(individual.getCnic());

            // Save job application
            return jobApplicationRepository.save(jobApplication);
        } else {
            throw new RuntimeException("Individual not found");
        }
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
        return jobApplicationRepository.findByJob_Company_CompanyName(companyName);
    }

    // Fetch job application details by ID
    public Optional<JobApplicationEntity> getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
    }
}
