package com.example.employeemanagement.dto;

public class EmployeeDTO {
    private String employmentCode;
    private String fullName;
    private String companyEmail;
    private String reportingManagerName;
    private String currentProjectName;

    // Constructor
    public EmployeeDTO(String employmentCode, String fullName, String companyEmail, String reportingManagerName, String currentProjectName) {
        this.employmentCode = employmentCode;
        this.fullName = fullName;
        this.companyEmail = companyEmail;
        this.reportingManagerName = reportingManagerName;
        this.currentProjectName = currentProjectName;
    }

    // Getters
    public String getEmploymentCode() {
        return employmentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getReportingManagerName() {
        return reportingManagerName;
    }

    public String getCurrentProjectName() {
        return currentProjectName;
    }

    // Setters
    public void setEmploymentCode(String employmentCode) {
        this.employmentCode = employmentCode;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public void setReportingManagerName(String reportingManagerName) {
        this.reportingManagerName = reportingManagerName;
    }

    public void setCurrentProjectName(String currentProjectName) {
        this.currentProjectName = currentProjectName;
    }
}