package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Long> {
    List<EmploymentHistory> findByEmploymentCode(String employmentCode);
    void deleteByEmploymentCode(String employmentCode);
}