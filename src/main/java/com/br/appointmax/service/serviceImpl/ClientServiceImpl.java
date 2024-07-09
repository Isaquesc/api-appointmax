package com.br.appointmax.service.serviceImpl;


import com.br.appointmax.model.Client;
import com.br.appointmax.repository.ClientRepository;
import com.br.appointmax.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
