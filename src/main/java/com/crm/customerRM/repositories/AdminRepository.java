package com.crm.customerRM.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crm.customerRM.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
