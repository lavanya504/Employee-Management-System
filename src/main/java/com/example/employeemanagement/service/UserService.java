package com.example.employeemanagement.service;

public interface UserService {
    boolean authenticate(String username, String password);
    String getUserRole(String username);
}
