package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Store;
import com.crm.customerRM.models.StoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Store> getStores() {
        return storeModel.getStores();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/add")
    public Store insertStore(@RequestBody Store p) {
        return storeModel.createStore(p);
    }
}
