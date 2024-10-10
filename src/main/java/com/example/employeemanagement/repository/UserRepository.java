package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Add this method to get only employees
    @Query("SELECT u FROM User u WHERE u.role = 'ROLE_EMPLOYEE'")
    List<User> findEmployees();

    User findByUsername(String username);
    User findByEmploymentCode(String employmentCode);
//    List<User> findAllByRole(User.Role role);
    void deleteByEmploymentCode(String employmentCode);

    // Add the method to check if a user exists by employment code
    boolean existsByEmploymentCode(String employmentCode);
}