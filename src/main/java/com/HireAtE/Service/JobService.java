package com.HireAtE.Service;

import com.HireAtE.Models.JobApplicationEntity;
import com.HireAtE.Models.JobEntity;
import com.HireAtE.Models.IndividualOnboardingEntity;
import com.HireAtE.Repository.JobApplicationRepository;
import com.HireAtE.Repository.JobRepository;
import com.HireAtE.Repository.IndividualOnboardingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final IndividualOnboardingRepository individualOnboardingRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public JobService(JobRepository jobRepository, IndividualOnboardingRepository individualOnboardingRepository,
            JobApplicationRepository jobApplicationRepository) {
        this.jobRepository = jobRepository;
        this.individualOnboardingRepository = individualOnboardingRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    // Apply for a job
    public String applyForJob(Long individualId, Long jobId) {
        Optional<JobEntity> job = jobRepository.findById(jobId);
        Optional<IndividualOnboardingEntity> individual = individualOnboardingRepository.findById(individualId);

        if (job.isPresent() && individual.isPresent()) {
            JobApplicationEntity jobApplication = new JobApplicationEntity();
            jobApplication.setJob(job.get());
            jobApplication.setIndividual(individual.get());
            jobApplication.setApplicationStatus(JobApplicationEntity.ApplicationStatus.APPLIED);
            jobApplication.setApplicationMessage("Successfully applied for " + job.get().getJobTitle());

            jobApplicationRepository.save(jobApplication);
            return "Application submitted successfully.";
        } else {
            return "Job or Individual not found.";
        }
    }

    // Get job applications by status
    public List<JobApplicationEntity> getApplicationsByStatus(Long individualId,
            JobApplicationEntity.ApplicationStatus status) {
        return jobApplicationRepository.findByIndividualIdAndApplicationStatus(individualId, status);
    }

    // Get all jobs
    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll(); // Retrieve all job entries from the database
    }

    // Get jobs by category
    public List<JobEntity> getJobsByCategory(String category) {
        return jobRepository.findByCategory(category);
    }

    // Get jobs by city
    public List<JobEntity> getJobsByCity(String city) {
        return jobRepository.findByCity(city);
    }

    // Get jobs by company name
    public List<JobEntity> getJobsByCompany(String company) {
        return jobRepository.findJobsByCompanyName(company); // Corrected to match repository method
    }

    // Get jobs by category and city with pagination
    public Page<JobEntity> getJobsByCategoryAndCity(String category, String city, Pageable pageable) {
        return jobRepository.findByCategoryAndCity(category, city, pageable);
    }

    // Get details of a specific job by ID (for job description screen)
    public Optional<JobEntity> getJobById(Long jobId) {
        // Retrieve a specific job by its ID
        return jobRepository.findById(jobId);
    }
}
