package com.HireAtE.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Optional;

@Entity
@Table(name = "job_entity")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String jobDescription;
    private String category;
    private String city;

    @ManyToOne
    @JoinColumn(name = "company_id") // This will be the foreign key in the job_entity table
    private CompanyOnboardingEntity company;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CompanyOnboardingEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyOnboardingEntity company) {
        this.company = company;
    }

    // Method to get company name directly from the associated
    // CompanyOnboardingEntity
    public String getCompanyName() {
        return Optional.ofNullable(company)
                .map(CompanyOnboardingEntity::getCompanyName)
                .orElse(null);
    }
}
