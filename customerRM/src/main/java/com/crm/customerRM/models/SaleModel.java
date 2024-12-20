package com.crm.customerRM.models;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.Client;
import com.crm.customerRM.entities.InventoryItem;
import com.crm.customerRM.entities.Product;
import com.crm.customerRM.entities.Sale;
import com.crm.customerRM.entities.SaleItem;
import com.crm.customerRM.repositories.ClientRepository;
import com.crm.customerRM.repositories.SaleRepository;

@Service
public class SaleModel {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    private InventoryItemModel inventoryModel;

    public SaleModel(SaleRepository saleRepository, ClientRepository clientRepository, InventoryItemModel inventoryModel) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.inventoryModel = inventoryModel;
    }

    public Sale createSale(Sale sale) {
        System.out.println("Creating sale for client ID: " + sale);


        // Process each sale item
        for (int i = 0; i < sale.getItems().size(); i++) {
            SaleItem item = sale.getItems().get(i);

            if (item.getInventoryItem() == null) {
                throw new IllegalArgumentException("Inventory item not associated with sale item at index: " + i);
            }

            Long inventoryItemId = item.getInventoryItem().getId();
            InventoryItem inventoryItem = inventoryModel.getInventoryItemById(inventoryItemId);

            if (inventoryItem == null) {
                throw new IllegalArgumentException("Inventory item not found for ID: " + inventoryItemId);
            }

            Product product = inventoryItem.getProduct();
            int soldQuantity = item.getQuantity();
            int currentQuantity = inventoryItem.getQuantity();

            System.out.println("Processing sale item:");
            System.out.println("-- Product: " + product.getName());
            System.out.println("-- Current Quantity: " + currentQuantity);
            System.out.println("-- Sold Quantity: " + soldQuantity);

            if (currentQuantity < soldQuantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }

            int newQuantity = currentQuantity - soldQuantity;
            try {
                inventoryModel.updateProductQuantity(product.getId(), newQuantity);
                System.out.println("-- Inventory updated. New Quantity: " + newQuantity);
            } catch (Exception e) {
                throw new RuntimeException("Failed to update inventory for product: " + product.getName(), e);
            }

            item.setSale(sale);
        }

        System.out.println("All sale items processed. Saving the sale...");
        Sale savedSale = saleRepository.save(sale);
        System.out.println("Sale saved successfully with ID: " + savedSale.getId());

        return savedSale;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public List<Sale> getSalesByClientId(Long clientId) {
        return saleRepository.findByClientId(clientId);
    }

    public List<Sale> getSalesByDateRange(Date startDate, Date endDate) {
        return saleRepository.findByDateBetween(startDate, endDate);
    }

    public long countSalesByClientId(Long clientId) {
        return saleRepository.countByClientId(clientId);
    }

    public void deleteSaleById(Long saleId) {
        saleRepository.deleteById(saleId);
    }
    public Long  getTotalSales() {
        return saleRepository.count(); // Custom query method from repository
    }
    public  Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }
}


