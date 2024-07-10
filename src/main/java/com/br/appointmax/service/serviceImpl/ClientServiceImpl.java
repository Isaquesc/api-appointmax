package com.br.appointmax.service.serviceImpl;


import com.br.appointmax.model.Client;
import com.br.appointmax.repository.ClientRepository;
import com.br.appointmax.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client getClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client getClientByCity(String city) {
        return clientRepository.findByAddress_City(city)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

}
