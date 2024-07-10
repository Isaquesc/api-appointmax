package com.br.appointmax.domain.service.serviceImpl;


import com.br.appointmax.domain.model.Client;
import com.br.appointmax.domain.repository.ClientRepository;
import com.br.appointmax.domain.service.ClientService;
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
