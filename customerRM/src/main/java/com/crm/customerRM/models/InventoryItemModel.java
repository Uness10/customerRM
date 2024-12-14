package com.crm.customerRM.models;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.repositories.InventoryItemRepository;
@Service
public class InventoryItemModel {
    @Autowired
    private InventoryItemRepository repo;

    public void updateProductQuantity(Long id, int newQuantity) {
        repo.findById(id).get().setQuantity(newQuantity);
    }

    public InventoryItem getInventoryItemById(Long id) {
        return repo.findById(id).get();
    } 
}
