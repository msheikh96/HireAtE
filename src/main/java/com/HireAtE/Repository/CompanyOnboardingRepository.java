package com.HireAtE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HireAtE.Models.CompanyOnboardingEntity;

@Repository
public interface CompanyOnboardingRepository extends JpaRepository<CompanyOnboardingEntity, Long> {
    boolean existsBycompanyName(String companyName);

}


