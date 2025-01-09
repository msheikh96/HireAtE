package com.HireAtE.Models;


import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_IndividualOnboarding")
@Getter
@Setter
public class IndividualOnboardingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String province;
    private String city;
    private String cnic;
    private String phoneNo;
    private String email;
    private String password;
    private String educationLevel;
    private String degreeTitle;
    private String instituteName;
    private Date startDate;
    private Date completionDate;
    private Double cgpa;
    private String currentStatus;
    private Integer totalExperience;
    private String currentPosition;
    private String JobExperience;
}

