package com.HireAtE.Repository;

import com.HireAtE.Models.JobApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {

    // Find applications by individual email
    List<JobApplicationEntity> findByIndividual_Email(String userEmail); // Corrected method

    // Find applications by individual ID and application status
    List<JobApplicationEntity> findByIndividualIdAndApplicationStatus(Long individualId,
            JobApplicationEntity.ApplicationStatus status);

    // Find applications by job category
    List<JobApplicationEntity> findByJobCategory(String category);

    // Find applications by job city
    List<JobApplicationEntity> findByJobCity(String city);

    // Find applications by job company name
    List<JobApplicationEntity> findByJobCompanyName(String companyName);
}
