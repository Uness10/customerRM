package com.crm.customerRM.controller;

import com.crm.customerRM.entities.Client;
import com.crm.customerRM.models.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientModel clientModel;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    public List<Client> getClients() {
        return clientModel.getClients();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/add")
    public Client insertClient(@RequestBody Client client) {
        // Save the client to the repository
        return clientModel.createClient(client);
    }
}
