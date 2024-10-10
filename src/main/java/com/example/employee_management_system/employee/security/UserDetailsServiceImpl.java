package com.example.employee_management_system.employee.security;

import com.example.employee_management_system.employee.entity.Employee;
import com.example.employee_management_system.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByCompanyEmail(username)
                .orElseGet(() -> employeeRepository.findByCompanyEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + employee.getRole().toUpperCase()));

        return new org.springframework.security.core.userdetails.User(
                employee.getCompanyEmail(),
                employee.getPassword(),
                authorities
        );
    }
}