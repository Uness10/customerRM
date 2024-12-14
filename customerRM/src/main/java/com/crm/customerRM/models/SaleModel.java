package com.crm.customerRM.models;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Product;
import com.crm.customerRM.entities.Sale;
import com.crm.customerRM.entities.SaleItem;
import com.crm.customerRM.repositories.SaleRepository;

@Service
public class SaleModel {
    @Autowired
    private final SaleRepository repo ; 
    private final InventoryItemModel inventoryModel;

    public SaleModel(SaleRepository repo, InventoryItemModel inventoryModel) {
        this.repo = repo;
        this.inventoryModel = inventoryModel;
    }
    public Sale createSale(Sale sale) {
        for (int i = 0; i < sale.getItems().size(); i++) {
            SaleItem item = sale.getItems().get(i);
            Long id = item.getInventoryItem().getId();
            InventoryItem inv = inventoryModel.getInventoryItemById(id);
            Product product = inv.getProduct();

            int soldQuantity = item.getQuantity();
            int currentQuantity = inv.getQuantity();
            System.out.println("--" +currentQuantity);
            System.out.println("--" +soldQuantity);
            if(currentQuantity == -1) {
                throw new IllegalArgumentException("Product not found in inventory: " + product.getName());
            }
            int newQuantity = currentQuantity - soldQuantity;
            System.out.println("--" +newQuantity);

            // Validate stock availability
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }
    
            inventoryModel.updateProductQuantity(product.getId(), newQuantity);
    
            sale.getItems().get(i).setSale(sale);
        }
    
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

    public long countSalesByClientId(Long clientId) {
        return repo.countByClientId(clientId);
    }
    public void deleteSaleById(Long saleId) {
        repo.deleteById(saleId);
    }
    
}
