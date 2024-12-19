package com.crm.customerRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.exceptions.InvalidCredentialsException;
import com.crm.customerRM.models.AdminModel;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminModel adminModel;

    @Autowired
    public AdminController(AdminModel adminModel) {
        this.adminModel = adminModel;
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") Long id) {
        Admin admin = adminModel.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(404).body("Admin not found");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody Admin adminDetails) {
        try {
            Admin updatedAdmin = adminModel.updateAdmin(adminDetails);
            return ResponseEntity.ok(updatedAdmin); // Return the updated admin object
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Return an error if admin not found or update fails
        }
    }

    @GetMapping("/details/{username}")
    public ResponseEntity<?> getAdminDetails(@PathVariable("username") String username) {
        Admin admin;
        admin = adminModel.getAdminDetails(username);

        if (admin != null) {
            return ResponseEntity.ok(admin); // Return the admin object if found
        } else {
            return ResponseEntity.status(404).body("Admin not found with username: " + username);
        }
    }
}
