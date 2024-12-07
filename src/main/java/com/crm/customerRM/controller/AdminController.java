package com.crm.customerRM.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.models.AdminModel;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminModel adminModel;

    @Autowired
    public AdminController(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        try {
            adminModel.login(admin.getUsername(), admin.getPassword());
            return ResponseEntity.ok("Login successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
