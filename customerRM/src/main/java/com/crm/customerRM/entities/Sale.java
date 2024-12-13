package com.crm.customerRM.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Double totalAmount = 0.0;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;



    // Default Constructor
    public Sale() {
    }
    public Sale(Client client, Date date) {
        this.client = client;
        this.date = date;
        this.totalAmount = 0.0;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    // Calculate Total Amount
    public void calculateTotal() {
        this.totalAmount = 0.0; // Reset total amount
        for (SaleItem item : this.items) {
            this.totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }
    }
    
}
