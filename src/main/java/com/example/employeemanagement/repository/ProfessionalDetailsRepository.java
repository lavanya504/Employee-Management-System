package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.ProfessionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalDetailsRepository extends JpaRepository<ProfessionalDetails, Long> {
    ProfessionalDetails findByEmploymentCode(String employmentCode);
    void deleteByEmploymentCode(String employmentCode);
}