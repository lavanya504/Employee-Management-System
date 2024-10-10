package com.example.employeemanagement.utility;

import com.example.employeemanagement.model.User;
import com.example.employeemanagement.model.PersonalDetails;
import com.example.employeemanagement.model.ProfessionalDetails;
import com.example.employeemanagement.repository.UserRepository;
import com.example.employeemanagement.repository.PersonalDetailsRepository; // Add the repository for PersonalDetails
import com.example.employeemanagement.repository.ProfessionalDetailsRepository; // Add the repository for ProfessionalDetails
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseSeederRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PersonalDetailsRepository personalDetailsRepository; // Add repository for PersonalDetails
    private final ProfessionalDetailsRepository professionalDetailsRepository; // Add repository for ProfessionalDetails
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeederRunner(
            UserRepository userRepository,
            PersonalDetailsRepository personalDetailsRepository,
            ProfessionalDetailsRepository professionalDetailsRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.professionalDetailsRepository = professionalDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist before seeding
        if (userRepository.count() == 0) {
            seedInitialData();
        } else {
            System.out.println("Users already exist. No seeding required.");
        }
    }

    private void seedInitialData() {
        // Check if Admin user exists
        if (!userRepository.existsByEmploymentCode("000001")) {
            User admin = new User();
            admin.setEmploymentCode("000001");
            admin.setUsername("admin@company.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ROLE_ADMIN);
            userRepository.save(admin);
        }

        // Check if Employee 1 exists
        if (!userRepository.existsByEmploymentCode("000002")) {
            User employee1 = new User();
            employee1.setEmploymentCode("000002");
            employee1.setUsername("employee1@company.com");
            employee1.setPassword(passwordEncoder.encode("employee123"));
            employee1.setRole(User.Role.ROLE_EMPLOYEE);
            userRepository.save(employee1);

            PersonalDetails personalDetails1 = new PersonalDetails();
            personalDetails1.setEmploymentCode("000002");
            personalDetails1.setFullName("Employee One");
            personalDetails1.setDateOfBirth(LocalDate.of(1990, 1, 1));

            personalDetails1.setGender(PersonalDetails.Gender.MALE);
            personalDetails1.setAge(34);
            personalDetails1.setCurrentAddressLine1("123 Main St");
            personalDetails1.setCurrentCity("CityA");
            personalDetails1.setCurrentPincode("123456");
            personalDetails1.setPermanentAddressLine1("456 Secondary St");
            personalDetails1.setPermanentCity("CityB");
            personalDetails1.setPermanentPincode("654321");
            personalDetails1.setMobile("1234567890");
            personalDetails1.setPersonalEmail("employee1@personal.com");
            personalDetails1.setEmergencyContactName("Emergency One");
            personalDetails1.setEmergencyContactMobile("0987654321");

            personalDetailsRepository.save(personalDetails1);

            ProfessionalDetails professionalDetails1 = new ProfessionalDetails();
            professionalDetails1.setEmploymentCode("000002");
            professionalDetails1.setCompanyEmail("employee1@company.com");

            professionalDetails1.setOfficePhone("1234567890");
            professionalDetails1.setCity("CityA");
            professionalDetails1.setOfficeAddressLine1("123 Main St");
            professionalDetails1.setOfficePincode("123456");
            professionalDetails1.setReportingManagerCode("000001");
            professionalDetails1.setHrName("HR Admin");
            professionalDetails1.setDateOfJoining(LocalDate.of(2022, 1, 1));
            professionalDetails1.setReportingManagerName("Admin User");

            professionalDetailsRepository.save(professionalDetails1);
        }

        // Check if Employee 2 exists
        if (!userRepository.existsByEmploymentCode("000003")) {
            User employee2 = new User();
            employee2.setEmploymentCode("000003");
            employee2.setUsername("employee2@company.com");
            employee2.setPassword(passwordEncoder.encode("employee123"));
            employee2.setRole(User.Role.ROLE_EMPLOYEE);
            userRepository.save(employee2);

            PersonalDetails personalDetails2 = new PersonalDetails();
            personalDetails2.setEmploymentCode("000003");
            personalDetails2.setFullName("Employee Two");
            personalDetails2.setDateOfBirth(LocalDate.of(1992, 2, 2));

            personalDetails2.setGender(PersonalDetails.Gender.FEMALE);
            personalDetails2.setAge(32);
            personalDetails2.setCurrentAddressLine1("789 Tertiary St");
            personalDetails2.setCurrentCity("CityC");
            personalDetails2.setCurrentPincode("112233");
            personalDetails2.setPermanentAddressLine1("101 Quaternary St");
            personalDetails2.setPermanentCity("CityD");
            personalDetails2.setPermanentPincode("445566");
            personalDetails2.setMobile("9876543210");
            personalDetails2.setPersonalEmail("employee2@personal.com");
            personalDetails2.setEmergencyContactName("Emergency Two");
            personalDetails2.setEmergencyContactMobile("0123456789");

            personalDetailsRepository.save(personalDetails2);

            ProfessionalDetails professionalDetails2 = new ProfessionalDetails();
            professionalDetails2.setEmploymentCode("000003");
            professionalDetails2.setCompanyEmail("employee2@company.com");

            professionalDetails2.setOfficePhone("0987654321");
            professionalDetails2.setCity("CityC");
            professionalDetails2.setOfficeAddressLine1("789 Tertiary St");
            professionalDetails2.setOfficePincode("112233");
            professionalDetails2.setReportingManagerCode("000001");
            professionalDetails2.setHrName("HR Admin");
            professionalDetails2.setDateOfJoining(LocalDate.of(2023, 2, 1));
            professionalDetails2.setReportingManagerName("Admin User");

            professionalDetailsRepository.save(professionalDetails2);
        }

        System.out.println("Database has been seeded with initial users, personal details, and professional details.");
    }
}

