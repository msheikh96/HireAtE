package com.HireAtE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HireAtE.Models.CompanyOnboardingEntity;
import com.HireAtE.Models.JobEntity;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    // Simple queries
    List<JobEntity> findByCategory(String category);

    List<JobEntity> findByCity(String city);

    List<JobEntity> findByCompany(CompanyOnboardingEntity company);

    // Custom query to get jobs by company name
    @Query("SELECT j FROM JobEntity j WHERE j.company.companyName = :companyName")
    List<JobEntity> findJobsByCompanyName(@Param("companyName") String companyName);

    // Find jobs by category and city with pagination support
    Page<JobEntity> findByCategoryAndCity(String category, String city, Pageable pageable);

    // Optionally, you could add more queries, such as searching by multiple
    // criteria.
    // For example, find jobs by category, city, and company.
    @Query("SELECT j FROM JobEntity j WHERE j.category = :category AND j.city = :city AND j.company.companyName = :companyName")
    List<JobEntity> findByCategoryCityAndCompany(@Param("category") String category,
            @Param("city") String city,
            @Param("companyName") String companyName);
}
