package com.crm.customerRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crm.customerRM.entities.SaleItem;
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

}
