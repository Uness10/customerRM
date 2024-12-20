package com.crm.customerRM.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Store;
public interface StoreRepository extends JpaRepository<Store, Long> {
    long count();  // Count all stores

}
