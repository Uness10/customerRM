package com.crm.customerRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customerRM.repositories.SaleRepository;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private  SaleRepository repo; 


    
}
