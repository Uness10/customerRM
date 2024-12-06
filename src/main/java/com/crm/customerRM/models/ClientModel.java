package com.crm.customerRM.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerRM.repositories.ClientRepository;

@Service
public class ClientModel {


    @Autowired
    private ClientRepository clientRepo;

    @SuppressWarnings("rawtypes")
    public List Display_Clients(){
        return clientRepo.findAll();
    }
    
}
