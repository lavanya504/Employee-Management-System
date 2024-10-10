
package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.*;
import com.example.employeemanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    private ProfessionalDetailsRepository professionalDetailsRepository;

    @Autowired
    private FinanceDetailsRepository financeDetailsRepository;

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithDetails() {
        // Fetch only employees (excluding admins)
        List<User> employees = userRepository.findEmployees();

        // Convert them to EmployeeDTO
        return employees.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());

//        List<User> allUsers = userRepository.findAll();
//        return allUsers.stream()
//                .map(this::convertToEmployeeDTO)
//                .collect(Collectors.toList());
    }
    @Override
    public EmployeeDTO getEmployeeDetails(String employmentCode) {
        User user = userRepository.findByEmploymentCode(employmentCode);
        return convertToEmployeeDTO(user);
    }

    private EmployeeDTO convertToEmployeeDTO(User user) {
        // Get PersonalDetails using the employment code from User
        PersonalDetails personalDetails = personalDetailsRepository.findByEmploymentCode(user.getEmploymentCode());

        // Get ProfessionalDetails using the employment code
        ProfessionalDetails professionalDetails = professionalDetailsRepository.findByEmploymentCode(user.getEmploymentCode());

        // Get the current project details (if any)
        ProjectDetails currentProject = projectDetailsRepository.findCurrentProjectByEmploymentCode(user.getEmploymentCode());
        String currentProjectName = (currentProject != null) ? currentProject.getProjectName() : "N/A";

        // Create and return EmployeeDTO using the details
        return new EmployeeDTO(
                user.getEmploymentCode(),
                personalDetails != null ? personalDetails.getFullName() : "N/A",
                professionalDetails != null ? professionalDetails.getCompanyEmail() : "N/A",
                professionalDetails != null ? professionalDetails.getReportingManagerName() : "N/A",
                currentProjectName
        );
    }



    @Override
    @Transactional
    public void addEmployee(PersonalDetails personalDetails, ProfessionalDetails professionalDetails, FinanceDetails financeDetails) {
        // Save Personal, Professional, and Finance Details
        personalDetailsRepository.save(personalDetails);
        professionalDetailsRepository.save(professionalDetails);
        financeDetailsRepository.save(financeDetails);

        // Create the user with the role of EMPLOYEE
        User user = new User();
        user.setEmploymentCode(personalDetails.getEmploymentCode());
        user.setUsername(professionalDetails.getCompanyEmail());

        String password= passwordEncoder.encode(personalDetails.getEmploymentCode());
        // Hashing the password (initially set as the employment code)
        user.setPassword(password);
        user.setRole(User.Role.ROLE_EMPLOYEE);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateEmployee(String employmentCode, PersonalDetails personalDetails, ProfessionalDetails professionalDetails, FinanceDetails financeDetails) {
        // Optionally find the current records if you need to ensure you're updating the correct ones
        PersonalDetails existingPersonalDetails = personalDetailsRepository.findByEmploymentCode(employmentCode);
        ProfessionalDetails existingProfessionalDetails = professionalDetailsRepository.findByEmploymentCode(employmentCode);
        FinanceDetails existingFinanceDetails = financeDetailsRepository.findByEmploymentCode(employmentCode);

        // Update fields as necessary
        existingPersonalDetails.setFullName(personalDetails.getFullName());
        existingPersonalDetails.setDateOfBirth(personalDetails.getDateOfBirth());
        // Repeat for other fields...

        // Save the updates
        personalDetailsRepository.save(existingPersonalDetails);
        professionalDetailsRepository.save(existingProfessionalDetails);
        financeDetailsRepository.save(existingFinanceDetails);
    }

    @Override
    @Transactional
    public void deleteEmployee(String employmentCode) {
        // Deleting the employee and related details by employment code
        userRepository.deleteByEmploymentCode(employmentCode);
        personalDetailsRepository.deleteByEmploymentCode(employmentCode);
        professionalDetailsRepository.deleteByEmploymentCode(employmentCode);
        financeDetailsRepository.deleteByEmploymentCode(employmentCode);
        projectDetailsRepository.deleteByEmploymentCode(employmentCode);
        employmentHistoryRepository.deleteByEmploymentCode(employmentCode);
    }

    @Override
    public PersonalDetails getPersonalDetails(String employmentCode) {
        return personalDetailsRepository.findByEmploymentCode(employmentCode);
    }

    @Override
    public ProfessionalDetails getProfessionalDetails(String employmentCode) {
        return professionalDetailsRepository.findByEmploymentCode(employmentCode);
    }

    @Override
    public FinanceDetails getFinanceDetails(String employmentCode) {
        return financeDetailsRepository.findByEmploymentCode(employmentCode);
    }

    @Override
    public PersonalDetails getPersonalDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return personalDetailsRepository.findByEmploymentCode(user.getEmploymentCode());
    }

    @Override
    public ProfessionalDetails getProfessionalDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return professionalDetailsRepository.findByEmploymentCode(user.getEmploymentCode());
    }

    @Override
    public FinanceDetails getFinanceDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return financeDetailsRepository.findByEmploymentCode(user.getEmploymentCode());
    }

    @Override
    public List<ProjectDetails> getProjectDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return projectDetailsRepository.findByEmploymentCode(user.getEmploymentCode());
    }

    @Override
    public List<EmploymentHistory> getEmploymentHistoryByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return employmentHistoryRepository.findByEmploymentCode(user.getEmploymentCode());
    }
}
