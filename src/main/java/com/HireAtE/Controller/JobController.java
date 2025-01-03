package com.HireAtE.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HireAtE.Models.JobEntity;
import com.HireAtE.Service.JobService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Get all jobs
    @GetMapping("/all")
    public ResponseEntity<List<JobEntity>> getAllJobs() {
        List<JobEntity> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Get jobs by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<JobEntity>> getJobsByCategory(@PathVariable String category) {
        List<JobEntity> jobs = jobService.getJobsByCategory(category);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Get jobs by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<JobEntity>> getJobsByCity(@PathVariable String city) {
        List<JobEntity> jobs = jobService.getJobsByCity(city);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Get jobs by company
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<JobEntity>> getJobsByCompany(@PathVariable String companyName) {
        List<JobEntity> jobs = jobService.getJobsByCompany(companyName);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Get details of a specific job by ID (for job description screen)
    @GetMapping("/{id}")
    public ResponseEntity<JobEntity> getJobById(@PathVariable Long id) {
        Optional<JobEntity> job = jobService.getJobById(id);
        if (job.isPresent()) {
            return new ResponseEntity<>(job.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Apply for a job
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<String> applyForJob(@PathVariable Long jobId, @RequestBody JobEntity jobApplication) {
        Optional<JobEntity> job = jobService.getJobById(jobId);
        
        if (!job.isPresent()) {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

        // Process the application and update the job status
        JobEntity jobEntity = job.get();
        jobEntity.setApplicationMessage("CONGRATULATIONS you have successfully applied for the position of " +
                jobEntity.getJobTitle() + " at " + jobEntity.getCompanyName());
        
        // Save updated job entity (application message added)
        jobService.updateJob(jobEntity);

        return new ResponseEntity<>(jobEntity.getApplicationMessage(), HttpStatus.OK);
    }
}

