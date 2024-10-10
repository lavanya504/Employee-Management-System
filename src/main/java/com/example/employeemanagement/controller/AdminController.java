package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.*;
import com.example.employeemanagement.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.io.Console;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String dashboard(Model model) throws JsonProcessingException {
//        List<User> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employees = employeeService.getAllEmployeesWithDetails();
        model.addAttribute("employees", employees);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(employees);
            System.out.println("Serialized JSON: " + json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/dashboard";
    }

    @GetMapping("/employee/view/{employmentCode}")
    public String viewEmployee(@PathVariable String employmentCode, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeDetails(employmentCode);
        model.addAttribute("employee", employee);
        return "admin/employee-details";
    }

    @GetMapping("/employee/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("personalDetails", new PersonalDetails());
        model.addAttribute("professionalDetails", new ProfessionalDetails());
        model.addAttribute("financeDetails", new FinanceDetails());
        return "admin/employee-form";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@Valid @ModelAttribute PersonalDetails personalDetails,
                              @Valid @ModelAttribute ProfessionalDetails professionalDetails,
                              @Valid @ModelAttribute FinanceDetails financeDetails,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "admin/employee-form";
        }

        // Save the employee details
        employeeService.addEmployee(personalDetails, professionalDetails, financeDetails);

        return "redirect:/admin/dashboard";
    }


    @GetMapping("/employee/edit/{employmentCode}")
    public String editEmployeeForm(@PathVariable String employmentCode, Model model) {
        PersonalDetails personalDetails = employeeService.getPersonalDetails(employmentCode);
        ProfessionalDetails professionalDetails = employeeService.getProfessionalDetails(employmentCode);
        FinanceDetails financeDetails = employeeService.getFinanceDetails(employmentCode);
        model.addAttribute("personalDetails", personalDetails);
        model.addAttribute("professionalDetails", professionalDetails);
        model.addAttribute("financeDetails", financeDetails);
        return "admin/employee-form";
    }

    @PostMapping("/employee/edit/{employmentCode}")
    public String editEmployee(@PathVariable String employmentCode,
                               @Valid @ModelAttribute PersonalDetails personalDetails,
                               @Valid @ModelAttribute ProfessionalDetails professionalDetails,
                               @Valid @ModelAttribute FinanceDetails financeDetails,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "admin/employee-form";
        }
        employeeService.updateEmployee(employmentCode, personalDetails, professionalDetails, financeDetails);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/employee/delete/{employmentCode}")
    public String deleteEmployee(@PathVariable String employmentCode) {
        employeeService.deleteEmployee(employmentCode);
        return "redirect:/admin/dashboard";
    }
}