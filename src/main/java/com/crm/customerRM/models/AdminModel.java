package com.crm.customerRM.models;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.repositories.AdminRepository;

@Service
public class AdminModel {

    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String User_name, String pasword){
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(User_name);

        if (optionalAdmin.isPresent()) {
            Admin Admin_found = optionalAdmin.get();

            if (Admin_found.getPassword().equals(pasword)) {
                return Admin_found;
            }else{
                throw new RuntimeException("invalid Password");
            }
        }else{
            throw new RuntimeException("Admin not found");
        }

    }
}
