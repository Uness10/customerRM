package com.crm.customerRM.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.models.AdminModel;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AdminModel adminModel;

    @Autowired
    public AuthController(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        try {
            String username = admin.getUsername();
            String password = admin.getPassword();
            System.out.println(username+','+password);
            // Validate credentials using AdminModel
            Admin authenticatedAdmin = adminModel.login(username, password);

            if (authenticatedAdmin == null) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            // Mock JWT generation (replace with actual JWT generation logic)
            String token = "mock-jwt-token-for-" + username;
            return ResponseEntity.ok().body("Bearer " + token);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during login: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        try {
            Admin newAdmin = adminModel.register(admin);

            String token = "mock-jwt-token-for-" + newAdmin.getUsername();
            System.out.println(token);

            return ResponseEntity.ok().body("Bearer " + token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during registration: " + e.getMessage());
        }
    }
}