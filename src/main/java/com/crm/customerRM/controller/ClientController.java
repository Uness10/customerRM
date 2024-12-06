package com.crm.customerRM.controller;

import com.crm.customerRM.models.ClientModel;
import com.crm.customerRM.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientModel clientModel;

    // Method to handle GET request for displaying all clients
    @RequestMapping("/clients") // Use @GetMapping for GET requests
    public String displayClients(Model model , RedirectAttributes redirectAttributes) {
        @SuppressWarnings("unchecked")
        List<Client> clients = clientModel.Display_Clients();
        redirectAttributes.addFlashAttribute("clients", clients); // Fetch clients from the service // Add the client list to the model
        return "dashboard"; // Return the dashboard view with the client list
    }
    
}
