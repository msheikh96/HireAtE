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

    @ManyToOne
    @JoinColumn(name = "company_id") // New foreign key for company
    private CompanyOnboardingEntity company; // This is the new reference to the company

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private String applicationMessage;
    // Add CNIC to JobApplicationEntity
    private String individualCnic; // CNIC of the individual applying for the job

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
        // Automatically set the CNIC when setting the individual
        this.individualCnic = individual != null ? individual.getCnic() : null;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public CompanyOnboardingEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyOnboardingEntity company) {
        this.company = company;
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

    public String getIndividualCnic() {
        return individualCnic;
    }

    public void setIndividualCnic(String individualCnic) {
        this.individualCnic = individualCnic;
    }

    public enum ApplicationStatus {
        APPLIED, ACCEPTED, IN_REVIEW
    }
}
