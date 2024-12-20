package com.crm.customerRM.repositories;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crm.customerRM.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByNameContaining(String name);

    @Query("SELECT c FROM Client c WHERE (SELECT SUM(s.totalAmount) FROM Sale s WHERE s.client.id = c.id) > :threshold")
    List<Client> findClientsWithTotalSalesGreaterThan(@Param("threshold") double threshold);
    
    @Query("SELECT c FROM Client c WHERE NOT EXISTS (" + "SELECT s FROM Sale s WHERE s.client = c AND s.date >= :cutoffDate)")
    List<Client> findInactiveClientsSince(@Param("cutoffDate") Date sqlCutoffDate);

    long count();
    

}
