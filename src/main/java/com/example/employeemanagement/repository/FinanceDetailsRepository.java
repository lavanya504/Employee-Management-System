package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.FinanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceDetailsRepository extends JpaRepository<FinanceDetails, Long> {
    FinanceDetails findByEmploymentCode(String employmentCode);
    void deleteByEmploymentCode(String employmentCode);
}