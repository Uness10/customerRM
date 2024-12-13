package com.crm.customerRM.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.exceptions.InvalidCredentialsException;
import com.crm.customerRM.models.AdminModel;

@RestController
public class AdminController {

    private final AdminModel adminModel;

    @Autowired
    public AdminController(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    @PostMapping("/login")
    public ResponseEntity<?> connect(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Admin admin = adminModel.login(username, password);
            return ResponseEntity.ok(admin); // Return the admin object as a JSON response
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(401).body( e.getMessage()); // Return 401 Unauthorized with an error message
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        // If you need to return some data for the dashboard, do it here
        // For example, you could return a list of information or status
        return ResponseEntity.ok("Dashboard data here"); // Example response
    }
}
