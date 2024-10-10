package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.*;
import com.example.employeemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PayslipService payslipService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        PersonalDetails personalDetails = employeeService.getPersonalDetailsByUsername(username);
        ProfessionalDetails professionalDetails = employeeService.getProfessionalDetailsByUsername(username);
        FinanceDetails financeDetails = employeeService.getFinanceDetailsByUsername(username);
        List<ProjectDetails> projectDetails = employeeService.getProjectDetailsByUsername(username);
        List<EmploymentHistory> employmentHistory = employeeService.getEmploymentHistoryByUsername(username);

        model.addAttribute("personalDetails", personalDetails);
        model.addAttribute("professionalDetails", professionalDetails);
        model.addAttribute("financeDetails", financeDetails);
        model.addAttribute("projectDetails", projectDetails);
        model.addAttribute("employmentHistory", employmentHistory);

        return "employee/dashboard";
    }

    @GetMapping("/payslips")
    public String payslips(Model model, Principal principal) {
        String username = principal.getName();
        List<Payslip> payslips = payslipService.getLastSixMonthsPayslips(username);
        System.out.println("Payslips found: " + payslips.size());
        model.addAttribute("payslips", payslips);
        return "employee/payslips";
    }

    @GetMapping("/payslip/{id}")
    public ResponseEntity<Resource> downloadPayslip(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        return payslipService.downloadPayslip(id, username);
    }
}