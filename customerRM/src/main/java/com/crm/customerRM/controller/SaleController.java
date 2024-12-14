package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Sale;
import com.crm.customerRM.models.SaleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleModel saleModel;

    public SaleController(SaleModel saleModel) {
        this.saleModel = saleModel;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    public List<Sale> getSales() {
        return saleModel.getAllsales();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/add")
    public Sale insertSale(@RequestBody Sale p) {
        // Save the Sale to the repository
        return saleModel.createSale(p);
    }
}
