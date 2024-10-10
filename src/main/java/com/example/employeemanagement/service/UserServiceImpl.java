//// UserServiceImpl.java
//package com.example.employeemanagement.service;
//
//import com.example.employeemanagement.model.User;
//import com.example.employeemanagement.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public boolean authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        return user != null && passwordEncoder.matches(password, user.getPassword());
//    }
//
//    @Override
//    public String getUserRole(String username) {
//        User user = userRepository.findByUsername(username);
//        return user != null ? user.getRole().name() : null;
//    }
//
//    // Additional methods as needed
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public void updateUser(User user) {
//        userRepository.save(user);
//    }
//
//    public void deleteUser(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user != null) {
//            userRepository.delete(user);
//        }
//    }
//}
//

package com.example.employeemanagement.service;

import com.example.employeemanagement.model.User;
import com.example.employeemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public String getUserRole(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user.getRole().name() : null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name().substring(5)) // remove "ROLE_" for Spring Security
                .build();
    }

    // Additional methods as needed
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}

