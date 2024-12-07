package com.crm.customerRM.models;
import com.crm.customerRM.exceptions.InvalidCredentialsException;
import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.repositories.AdminRepository;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminModel {
    private final AdminRepository repo;

    @Autowired
    public AdminModel(AdminRepository repo) {
        this.repo = repo;
    }

    public Admin login(String User_name, String pasword) throws InvalidCredentialsException {
        Optional<Admin> optionalAdmin = repo.findByUsername(User_name);

        if (optionalAdmin.isPresent()) {
            Admin Admin_found = optionalAdmin.get();

            if (Admin_found.getPassword().equals(pasword)) {
                return Admin_found;
            }else{
                throw new InvalidCredentialsException("invalid Password");
            }
        }else{
            throw new InvalidCredentialsException("Username not found");
        }

    }
    public Admin register(Admin admin) {
        return repo.save(admin);
    }
    public Admin update(Admin admin) {
        return repo.save(admin);
    }
    
}
