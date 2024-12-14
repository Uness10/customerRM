package com.crm.customerRM.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.Client;
import com.crm.customerRM.repositories.ClientRepository;

@Service
public class ClientModel {
    @Autowired
    private ClientRepository repo;
    public ClientModel(ClientRepository repo) {
        this.repo = repo;
    }
    public List<Client> getClients() {
        return repo.findAll();
    }

    public Client createClient(Client client) {
        return repo.save(client);
    }
    
}
