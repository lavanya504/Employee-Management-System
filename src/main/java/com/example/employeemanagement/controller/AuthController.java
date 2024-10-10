//package com.example.employeemanagement.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AuthController {
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @GetMapping("/dashboard")
//    public String dashboard() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//            return "redirect:/admin/dashboard";
//        } else {
//            return "redirect:/employee/dashboard";
//        }
//    }
//
//    @GetMapping("/access-denied")
//    public String accessDenied() {
//        return "error/403";
//    }
//}

package com.example.employeemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {


    @GetMapping("/login")
    public String loginPage(){

        return "login";  // returns login.html template
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        // Optionally add attributes to the model if needed
        return "home";  // returns home.html template
    }

    @GetMapping("/dashboard")
    public String dashboard() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//            return "redirect:/admin/dashboard";
//        } else {
            return "redirect:/employee/dashboard";
//        }
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/403";
    }
}
