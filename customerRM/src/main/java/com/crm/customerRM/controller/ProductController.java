package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Product;
import com.crm.customerRM.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @CrossOrigin(origins = "http://localhost:8080")

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
