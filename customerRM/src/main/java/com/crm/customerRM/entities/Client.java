package com.crm.customerRM.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactInfo;

    @OneToMany(mappedBy = "client")
    private List<Sale> sales;

    // Default Constructor
    public Client() {
    }

    // Parameterized Constructor
    public Client(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
