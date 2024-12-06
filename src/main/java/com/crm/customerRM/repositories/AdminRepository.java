package com.crm.customerRM.repositories;

import java.util.Optional;

import com.crm.customerRM.entities.Admin;

public interface AdminRepository {
    Optional<Admin> findByUsername(String username);
}
