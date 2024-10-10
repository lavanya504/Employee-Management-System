package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;

public interface PayslipRepository extends JpaRepository<Payslip, Long> {
    List<Payslip> findByEmploymentCodeAndMonthGreaterThanEqual(String employmentCode, YearMonth month);
    Payslip findByIdAndEmploymentCode(Long id, String employmentCode);
}