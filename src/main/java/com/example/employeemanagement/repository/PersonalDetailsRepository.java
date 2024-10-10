package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {
    PersonalDetails findByEmploymentCode(String employmentCode);
    void deleteByEmploymentCode(String employmentCode);
}
