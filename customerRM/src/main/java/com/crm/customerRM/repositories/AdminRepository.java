package com.crm.customerRM.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crm.customerRM.entities.Admin;
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsernameAndPassword(String username, String password);

    Optional<Admin> findByUsername(String user_name) ;

    
    
}
