package com.crm.customerRM.models;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.Sale;
import com.crm.customerRM.repositories.SaleRepository;

@Service
public class SaleModel {
    @Autowired
    private final SaleRepository repo ; 

    public SaleModel(SaleRepository repo) {
        this.repo = repo;
    }
    // create new one or update existing one
    public Sale createSale(Sale sale) {
        return repo.save(sale);
    }
    public List<Sale> getAllsales() {
        return repo.findAll();
    }
    public List<Sale> getSalesByClientId(Long clientId) {
        return  repo.findByClientId(clientId) ;
    }
    public List<Sale> getSalesByDateRange(Date startDate, Date endDate) {
        return repo.findByDateBetween(startDate, endDate);
    }
    public List<Sale> getSalesByProductId(Long productId) {
        return repo.findByItemsProductId(productId);
    }
    public long countSalesByClientId(Long clientId) {
        return repo.countByClientId(clientId);
    }
    public void deleteSaleById(Long saleId) {
        repo.deleteById(saleId);
    }
    
}
