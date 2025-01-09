package com.HireAtE.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "job_applications") // to keep track of which user applied for which job and the status of their
                                  // application and will have a foreign key to job_entity
public class JobApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "individual_id")
    private IndividualOnboardingEntity individual;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity job;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private String applicationMessage;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IndividualOnboardingEntity getIndividual() {
        return individual;
    }

    public void setIndividual(IndividualOnboardingEntity individual) {
        this.individual = individual;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationMessage() {
        return applicationMessage;
    }

    public void setApplicationMessage(String applicationMessage) {
        this.applicationMessage = applicationMessage;
    }

    public enum ApplicationStatus {
        APPLIED, ACCEPTED, IN_REVIEW
    }
}

// This class connects IndividualOnboardingEntity and JobEntity.
// applicationStatus can be APPLIED, ACCEPTED, or IN_REVIEW.
// applicationMessage stores the message to be shown after applying for a job
// (like "Congratulations, you've applied successfully").