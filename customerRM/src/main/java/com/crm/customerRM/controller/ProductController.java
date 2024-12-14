package com.crm.customerRM.controller;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Product;
import com.crm.customerRM.models.ProductModel;
import com.crm.customerRM.models.StoreModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductModel productModel;

    @Autowired
    private StoreModel storeModel; // Add the missing annotation

    public ProductController(ProductModel productModel) {
        this.productModel = productModel;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productModel.getAllProducts();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/byStore/{storeId}")
    public List<Product> getProductByStoreId(@PathVariable Long storeId) {

        List<InventoryItem> invItems = storeModel.getInventoriesByStoryId(storeId);
        List<Product> products = new ArrayList<>();
    
        for (InventoryItem invItem : invItems) {
            products.add(invItem.getProduct());
        }
    
        return products;
    }
    

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/add")
    public Product insertProduct(@RequestBody Product p) {
        // Save the product to the repository
        return productModel.createProduct(p);
    }
}
