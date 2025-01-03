package com.HireAtE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HireAtE.Models.JobEntity;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    List<JobEntity> findByCategory(String category);
    List<JobEntity> findByCity(String city);
    List<JobEntity> findByCompanyName(String companyName);
}
