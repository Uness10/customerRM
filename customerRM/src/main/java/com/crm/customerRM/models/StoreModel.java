package com.crm.customerRM.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Store;
import com.crm.customerRM.repositories.StoreRepository;

@Service
public class StoreModel {
    @Autowired
    private StoreRepository repo;
    public StoreModel(StoreRepository repo) {
        this.repo = repo;
    }
    public List<Store> getStores() {
        return repo.findAll();
    }
    public List<InventoryItem> getInventoriesByStoryId(Long id) {
        return repo.findById(id).get().getInventory();
    }

    public Store createStore(Store store) {
        return repo.save(store);
    }
    public void deleteStore(Long storeId) {
        repo.deleteById(storeId);
    }
    public Store addItem(Long storeId, InventoryItem inventoryItem) {
        Store store = repo.findById(storeId).orElseThrow(() -> 
            new IllegalArgumentException("Store with ID " + storeId + " not found."));
    
        inventoryItem.setStore(store);
        System.out.println(inventoryItem);
        store.addInventory(inventoryItem);
    
        // Save the inventory item
        return repo.save(store);
    }
    

    
}
