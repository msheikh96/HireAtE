package com.HireAtE.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HireAtE.Models.JobEntity;
import com.HireAtE.Response.APIResponseClass;
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
    public ResponseEntity<APIResponseClass> getAllJobs() {
        List<JobEntity> jobs = jobService.getAllJobs();
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(new APIResponseClass("No jobs found.", "404"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new APIResponseClass("Jobs retrieved successfully.", "200"), HttpStatus.OK);
    }

    // Get jobs by category
    @GetMapping("/category/{category}")
    public ResponseEntity<APIResponseClass> getJobsByCategory(@PathVariable String category) {
        List<JobEntity> jobs = jobService.getJobsByCategory(category);
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(new APIResponseClass("No jobs found in this category.", "404"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new APIResponseClass("Jobs retrieved successfully.", "200"), HttpStatus.OK);
    }

    // Get jobs by city
    @GetMapping("/city/{city}")
    public ResponseEntity<APIResponseClass> getJobsByCity(@PathVariable String city) {
        List<JobEntity> jobs = jobService.getJobsByCity(city);
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(new APIResponseClass("No jobs found in this city.", "404"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new APIResponseClass("Jobs retrieved successfully.", "200"), HttpStatus.OK);
    }

    // Get jobs by company
    @GetMapping("/company/{companyName}")
    public ResponseEntity<APIResponseClass> getJobsByCompany(@PathVariable String companyName) {
        List<JobEntity> jobs = jobService.getJobsByCompany(companyName);
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(new APIResponseClass("No jobs found for this company.", "404"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new APIResponseClass("Jobs retrieved successfully.", "200"), HttpStatus.OK);
    }

    // Get details of a specific job by ID (for job description screen)
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseClass> getJobById(@PathVariable Long id) {
        Optional<JobEntity> job = jobService.getJobById(id);
        if (job.isPresent()) {
            return new ResponseEntity<>(new APIResponseClass("Job retrieved successfully.", "200"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new APIResponseClass("Job not found.", "404"), HttpStatus.NOT_FOUND);
        }
    }

    // Apply for a job
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<APIResponseClass> applyForJob(@PathVariable Long jobId,
            @RequestBody JobEntity jobApplication) {
        Optional<JobEntity> job = jobService.getJobById(jobId);

        if (!job.isPresent()) {
            return new ResponseEntity<>(new APIResponseClass("Job not found.", "404"), HttpStatus.NOT_FOUND);
        }

        // Process the application and update the job status
        JobEntity jobEntity = job.get();
        jobEntity.setApplicationMessage("CONGRATULATIONS! You have successfully applied for the position of " +
                jobEntity.getJobTitle() + " at " + jobEntity.getCompanyName());

        // Save updated job entity (application message added)
        jobService.updateJob(jobEntity);

        return new ResponseEntity<>(new APIResponseClass(jobEntity.getApplicationMessage(), "200"), HttpStatus.OK);
    }
}
