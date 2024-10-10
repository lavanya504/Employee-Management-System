package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Payslip;
import com.example.employeemanagement.model.User;
import com.example.employeemanagement.repository.PayslipRepository;
import com.example.employeemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.List;

@Service
public class PayslipServiceImpl implements PayslipService {

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Payslip> getLastSixMonthsPayslips(String username) {
        User user = userRepository.findByUsername(username);
        YearMonth sixMonthsAgo = YearMonth.now().minusMonths(6);
        return payslipRepository.findByEmploymentCodeAndMonthGreaterThanEqual(user.getEmploymentCode(), sixMonthsAgo);
    }

    @Override
    public ResponseEntity<Resource> downloadPayslip(Long id, String username) {
        User user = userRepository.findByUsername(username);
        Payslip payslip = payslipRepository.findByIdAndEmploymentCode(id, user.getEmploymentCode());

        if (payslip == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path file = Paths.get(payslip.getPayslipFilePath());
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}