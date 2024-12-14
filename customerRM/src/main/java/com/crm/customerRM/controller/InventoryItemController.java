package com.crm.customerRM.controller;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.models.InventoryItemModel;
import com.crm.customerRM.models.StoreModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventoryItems")
public class InventoryItemController {

    @Autowired
    private InventoryItemModel inventoryItemModel;

    @Autowired
    private StoreModel storeModel; // Add the missing annotation

    public InventoryItemController(InventoryItemModel inventoryItemModel) {
        this.inventoryItemModel = inventoryItemModel;
    }



    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/byStore/{storeId}")
    public List<InventoryItem> getInventoryItemByStoreId(@PathVariable Long storeId) {

        List<InventoryItem> invItems = storeModel.getInventoriesByStoryId(storeId);
        
        return invItems;
    }
}


    