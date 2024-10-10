package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Long> {
    List<ProjectDetails> findByEmploymentCode(String employmentCode);
    void deleteByEmploymentCode(String employmentCode);

    @Query("SELECT p FROM ProjectDetails p WHERE p.employmentCode = :employmentCode AND p.endDate IS NULL ORDER BY p.startDate DESC")
    ProjectDetails findCurrentProjectByEmploymentCode(@Param("employmentCode") String employmentCode);
}