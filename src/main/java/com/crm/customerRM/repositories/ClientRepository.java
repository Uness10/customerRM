package com.crm.customerRM.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.customerRM.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
