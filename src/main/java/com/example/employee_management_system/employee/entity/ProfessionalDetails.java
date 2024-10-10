package com.example.employee_management_system.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String officePhone;
    private String officeAddress;
    private String reportingManagerEmail;
    private String hrName;
    private String employmentHistory; // You can store this as a String or as a separate entity if needed
    private LocalDate dateOfJoining;
}
