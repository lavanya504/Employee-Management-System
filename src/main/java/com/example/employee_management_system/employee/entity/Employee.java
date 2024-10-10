package com.example.employee_management_system.employee.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String gender;

    private LocalDate dob;

    @Column(nullable = false, unique = true)
    private String employmentCode;

    private String mobile;

    @Column(nullable = false, unique = true)
    private String companyEmail;

    @Column(nullable = false)
    private String password; // Password should be hashed before saving

    // Role should only be "ADMIN" or "EMPLOYEE"
    @Column(nullable = false)
    private String role; // Single role, either "ADMIN" or "EMPLOYEE"

    @OneToOne(cascade = CascadeType.ALL)
    private ProfessionalDetails professionalDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private FinanceDetails financeDetails;
}
