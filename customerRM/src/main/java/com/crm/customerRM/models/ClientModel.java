package com.crm.customerRM.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.entities.Client;
import com.crm.customerRM.entities.Sale;
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
    public Optional<Client> getClientById(Long id) {
        return repo.findById(id);
    }
    public Client updateClient(Client updatedClient) {
        return repo.save(updatedClient);
    }
    public void deleteClient(Long id) {
        repo.deleteById(id);
    }
    public List<Client> searchClientsByName(String name) {
        return repo.findByNameContaining(name);
    }
    public List<Client> getHighSpenders(double threshold) {
        return repo.findClientsWithTotalSalesGreaterThan(threshold);
    }
    public List<Client> getInactiveClients(int days) {
        // Calculate the cutoff date
        LocalDate cutoffDate = LocalDate.now().minusDays(days);
    
        // Convert LocalDate to java.sql.Date
        Date sqlCutoffDate = Date.valueOf(cutoffDate);  // Convert LocalDate to java.sql.Date
    
        // Call the repository method with the correct parameter type
        return repo.findInactiveClientsSince(sqlCutoffDate);
    }


    
}
