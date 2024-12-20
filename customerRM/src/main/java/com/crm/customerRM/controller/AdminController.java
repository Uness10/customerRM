package com.crm.customerRM.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.exceptions.InvalidCredentialsException;
import com.crm.customerRM.models.AdminModel;
import com.crm.customerRM.models.ClientModel;
import com.crm.customerRM.models.SaleModel;
import com.crm.customerRM.models.StoreModel;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminModel adminModel;

    private final ClientModel clientModel; 
    private final SaleModel saleModel;     
    private final StoreModel storeModel;  
    @Autowired
    public AdminController(AdminModel adminModel, ClientModel clientModel, SaleModel saleModel, StoreModel storeModel) {
        this.adminModel = adminModel;
        this.clientModel = clientModel;
        this.saleModel = saleModel;
        this.storeModel = storeModel;
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") Long id) {
        Admin admin = adminModel.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(admin.getUsername());
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
    @GetMapping("/stats")
    public ResponseEntity<List<Long>> getTotals() {
        // Fetch totals
        long totalClients = clientModel.getTotalClients();
        long totalSales = saleModel.getTotalSales();
        long totalStores = storeModel.getTotalStores();
        
        // Create a List to hold the stats
        List<Long> stats = new ArrayList<>();
        stats.add(totalClients);
        stats.add(totalSales);
        stats.add(totalStores);
        
        // Return the stats list wrapped in a ResponseEntity
        return ResponseEntity.ok(stats);
    }

}
