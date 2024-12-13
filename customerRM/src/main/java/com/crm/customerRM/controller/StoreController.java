package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Store;
import com.crm.customerRM.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository repo;

    @GetMapping("/api/stores")
    public List<Store> getStores() {
        return repo.findAll();
    }
}
