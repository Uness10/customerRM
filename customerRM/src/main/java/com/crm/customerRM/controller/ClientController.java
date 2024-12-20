package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Client;
import com.crm.customerRM.entities.Sale;
import com.crm.customerRM.models.ClientModel;
import com.crm.customerRM.models.SaleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientModel clientModel;
    @Autowired
    private SaleModel   saleModel;

    // Fetch all clients
    @GetMapping("/all")
    public List<Client> getClients() {
        return clientModel.getClients();
    }

    // Add a new client
    @PostMapping("/add")
    public Client insertClient(@RequestBody Client client) {
        return clientModel.createClient(client);
    }

    // Fetch a client by ID
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientModel.getClientById(id);
    }

    // Update a client's details
    @PutMapping("/update")
    public Client updateClient(@RequestBody Client updatedClient) {
        return clientModel.updateClient(updatedClient);
    }

    // Delete a client
    @DeleteMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientModel.deleteClient(id);
        return "Client with ID " + id + " deleted successfully!";
    }

    // Search clients by name
    @GetMapping("/search")
    public List<Client> searchClientsByName(@RequestParam String name) {
        return clientModel.searchClientsByName(name);
    }

    // Fetch clients with high spending (based on a threshold)
    @GetMapping("/highSpenders")
    public List<Client> getHighSpenders(@RequestParam double threshold) {
        return clientModel.getHighSpenders(threshold);
    }

    // Fetch inactive clients (e.g., no purchases in the last X days)
    @GetMapping("/inactive")
    public List<Client> getInactiveClients(@RequestParam int days) {
        return clientModel.getInactiveClients(days);
    }

    // Fetch client purchase history
    @GetMapping("/history/{id}")
    public List<Sale> getClientPurchaseHistory(@PathVariable Long id) {
        return saleModel.getSalesByClientId(id);
    }
}
