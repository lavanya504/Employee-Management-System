package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.*;
import java.util.List;

public interface EmployeeService {
    List<User> getAllEmployees();
    List<EmployeeDTO> getAllEmployeesWithDetails();
    EmployeeDTO getEmployeeDetails(String employmentCode);
    void addEmployee(PersonalDetails personalDetails, ProfessionalDetails professionalDetails, FinanceDetails financeDetails);
    void updateEmployee(String employmentCode, PersonalDetails personalDetails, ProfessionalDetails professionalDetails, FinanceDetails financeDetails);
    void deleteEmployee(String employmentCode);
    PersonalDetails getPersonalDetails(String employmentCode);
    ProfessionalDetails getProfessionalDetails(String employmentCode);
    FinanceDetails getFinanceDetails(String employmentCode);
    PersonalDetails getPersonalDetailsByUsername(String username);
    ProfessionalDetails getProfessionalDetailsByUsername(String username);
    FinanceDetails getFinanceDetailsByUsername(String username);
    List<ProjectDetails> getProjectDetailsByUsername(String username);
    List<EmploymentHistory> getEmploymentHistoryByUsername(String username);
}