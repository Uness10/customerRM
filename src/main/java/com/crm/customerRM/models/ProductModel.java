package com.crm.customerRM.models;

import com.crm.customerRM.entities.Product;
import com.crm.customerRM.repositories.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductModel {
    @Autowired
    private final ProductRepository repo ;

    public ProductModel(ProductRepository repo) {
        this.repo = repo;
    }

    public Product createProduct(Product product) {
        return repo.save(product);
    }
    public Product updateProduct(Product product) {
        return repo.save(product);
    }
    public List<Product> getAllProduct(Long id) {
        return repo.findAll();
    }

}
