package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Payslip;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PayslipService {
    List<Payslip> getLastSixMonthsPayslips(String username);
    ResponseEntity<Resource> downloadPayslip(Long id, String username);
}