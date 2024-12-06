package com.crm.customerRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.customerRM.entities.InventoryItem;
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

}
