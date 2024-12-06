package com.crm.customerRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.models.AdminModel;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    
    @Autowired
    private AdminModel adminmodel;

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestParam String username, @RequestParam String password){
        try {
            Admin admin = adminmodel.login(username, password);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(null);
        }
    }


}
