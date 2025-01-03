package com.HireAtE.Service;

import org.springframework.stereotype.Service;

import com.HireAtE.Models.JobEntity;
import com.HireAtE.Repository.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<JobEntity> getJobsByCategory(String category) {
        return jobRepository.findByCategory(category);
    }

    public List<JobEntity> getJobsByCity(String city) {
        return jobRepository.findByCity(city);
    }

    public List<JobEntity> getJobsByCompany(String companyName) {
        return jobRepository.findByCompanyName(companyName);
    }

    // Fetch job details by ID (for job description screen)
    public Optional<JobEntity> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    // Update job entity after application
    public JobEntity updateJob(JobEntity jobEntity) {
        return jobRepository.save(jobEntity);  // Save updated job entity
    }
}
