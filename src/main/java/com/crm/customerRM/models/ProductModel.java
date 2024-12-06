package com.crm.customerRM.models;

import com.crm.customerRM.repositories.ProductRepository;

public class ProductModel {
    private final ProductRepository repo ;

    public ProductModel(ProductRepository repo) {
        this.repo = repo;
    }
}
