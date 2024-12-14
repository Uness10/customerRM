package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Product;
import com.crm.customerRM.models.ProductModel;
import com.crm.customerRM.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductModel productModel;

    public ProductController(ProductModel productModel) {
        this.productModel = productModel;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productModel.getAllProducts();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/add")
    public Product insertProduct(@RequestBody Product p) {
        // Save the product to the repository
        return productModel.createProduct(p);
    }
}
