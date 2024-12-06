package com.crm.customerRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crm.customerRM.entities.Admin;
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
