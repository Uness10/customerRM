package com.crm.customerRM.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crm.customerRM.entities.Sale;
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByClientId(Long clientId);

    List<Sale> findByDateBetween(Date startDate, Date endDate);

   // List<Sale> findByItemsProductId(Long productId);

    long countByClientId(Long clientId);


}
