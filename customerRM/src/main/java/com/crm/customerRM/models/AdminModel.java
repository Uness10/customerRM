package com.crm.customerRM.models;

import com.crm.customerRM.exceptions.InvalidCredentialsException;
import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminModel {

    private final AdminRepository repo;

    @Autowired
    public AdminModel(AdminRepository repo) {
        this.repo = repo;
    }

    public Admin login(String username, String password)  {
        Optional<Admin> optionalAdmin = repo.findByUsername(username);

        if (optionalAdmin.isPresent()) {
            Admin adminFound = optionalAdmin.get();
            if (password.equals(adminFound.getPassword())) {
                return adminFound;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Admin register(Admin admin) {
        admin.setPassword(admin.getPassword());
        return repo.save(admin);
    }

    public Admin updateAdmin(Admin adminDetails) throws InvalidCredentialsException {
        Optional<Admin> optionalAdmin = repo.findByUsername(adminDetails.getUsername());

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();

            // Only update the password if it's provided
            if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
                admin.setPassword(adminDetails.getPassword());
            }

            return repo.save(admin);
        } else {
            throw new InvalidCredentialsException("Admin not found with username: " + adminDetails.getUsername());
        }
    }

    public Admin getAdminById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Admin getAdminDetails(String username)  {
        Optional<Admin> optionalAdmin = repo.findByUsername(username);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        } else {
           return null;
        }
    }
}
