package com.crm.customerRM.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Store;
import com.crm.customerRM.repositories.StoreRepository;

public class StoreModel {
    @Autowired
    private StoreRepository repo;
    public StoreModel(StoreRepository repo) {
        this.repo = repo;
    }
    public List<Store> getStores() {
        return repo.findAll();
    }
    public List<InventoryItem> getInventoriesByStore(Long id) {
        return repo.findById(id).get().getInventory();
    }
    
}