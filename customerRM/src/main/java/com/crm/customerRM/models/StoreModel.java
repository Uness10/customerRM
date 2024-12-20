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
        // Find the store by ID, or throw an exception if not found
        Store store = repo.findById(storeId).orElseThrow(() -> 
            new IllegalArgumentException("Store with ID " + storeId + " not found."));

        // Check if the product already exists in the store inventory
        for (InventoryItem inv : store.getInventory()) {
            if (inv.getProduct().getId().equals(inventoryItem.getProduct().getId())) {
                // If product exists, update the quantity
                inv.setQuantity(inv.getQuantity() + inventoryItem.getQuantity());
                return repo.save(store); // Save and return updated store
            }
        }

        // If the product does not exist, add a new InventoryItem
        inventoryItem.setStore(store);
        store.addInventory(inventoryItem);

        // Save the store with the new inventory item
        return repo.save(store);
    }
    public Long getTotalStores() {
        return repo.count(); // Custom query method from repository
    }
}

 