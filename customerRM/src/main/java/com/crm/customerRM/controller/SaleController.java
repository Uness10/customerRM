package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Sale;
import com.crm.customerRM.models.SaleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleModel saleModel;

    public SaleController(SaleModel saleModel) {
        this.saleModel = saleModel;
    }
    @GetMapping("/all")
    public List<Sale> getSales() {
        return saleModel.getAllSales();
    }

    @PostMapping("/add")
    public Sale insertSale(@RequestBody Sale p) {
        System.out.println("hi");
        
        return saleModel.createSale(p);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        saleModel.deleteSaleById(id);
        return "sale with ID " + id + " deleted successfully!";
    }
    @GetMapping("/{saleId}")
    public Optional<Sale> getSaleById(@PathVariable Long saleId) {
        return saleModel.getSaleById(saleId);
    }
}