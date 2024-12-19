package com.crm.customerRM.controller;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Store;
import com.crm.customerRM.models.InventoryItemModel;
import com.crm.customerRM.models.StoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores")

public class StoreController {

    @Autowired
    private StoreModel storeModel;
    
    public StoreController(StoreModel storeModel) {
        this.storeModel = storeModel;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    public List<Store> getAllStores() {
        return storeModel.getStores();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/add")
    public Store insertStore(@RequestBody Store p) {
        return storeModel.createStore(p);
    }


    @GetMapping("/{storeId}/inventory")
    public List<InventoryItem> getInventoryByStore(
        @PathVariable Long storeId,
        @RequestParam(required = false) Integer minQuantity,
        @RequestParam(required = false) Double maxPrice) {

        List<InventoryItem> inventory = storeModel.getInventoriesByStoryId(storeId);

        if (minQuantity != null) {
            inventory = inventory.stream()
                .filter(item -> item.getQuantity() >= minQuantity)
                .collect(Collectors.toList());
        }

        if (maxPrice != null) {
            inventory = inventory.stream()
                .filter(item -> item.getProduct().getPrice() <= maxPrice)
                .collect(Collectors.toList());
        }

        return inventory;
    }
    @DeleteMapping("/{storeId}/delete")
    public ResponseEntity<?> deleteStore(@PathVariable Long storeId) {
        storeModel.deleteStore(storeId);
        return ResponseEntity.ok("Store deleted successfully");
    }
    @PostMapping("/{storeId}/addInventory")
    public Store addInventoryItemToStore(
        @PathVariable Long storeId,
        @RequestBody InventoryItem inventoryItem) {
        return storeModel.addItem(storeId, inventoryItem);
    }
    



}
