package com.example.employee_management_system.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee_management_system.employee.entity.Employee;
import com.example.employee_management_system.employee.exception.ResourceNotFoundException;
import com.example.employee_management_system.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Employee registerNewEmployee(Employee employee) {
        // Encode the password
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        // Set a default role (you might want to adjust this based on your needs)
        employee.setRole("EMPLOYEE");

        // Generate an employment code (you might want to implement a more sophisticated method)
        employee.setEmploymentCode("EMP" + System.currentTimeMillis());

        return employeeRepository.save(employee);
    }

//    public Optional<Employee> getEmployeeByEmailOrCode(String emailOrCode) {
//        return employeeRepository.findByCompanyEmail(emailOrCode)
//                .or(() -> employeeRepository.findByEmploymentCode(emailOrCode));
//    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Create employee
    public Employee createEmployee(Employee employee) {
        // Hash the password before saving (if not already hashed)
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByCompanyEmail(email).orElse(null);
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        // Update employee details here...
        employee.setFullName(employeeDetails.getFullName());
        employee.setCompanyEmail(employeeDetails.getCompanyEmail());
        employee.setEmploymentCode(employeeDetails.getEmploymentCode());
        employee.setDob(employeeDetails.getDob());
        employee.setRole(employeeDetails.getRole());
        employee.setMobile(employeeDetails.getMobile());
        // Save the updated employee
        return employeeRepository.save(employee);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.deleteById(id);
    }
}
