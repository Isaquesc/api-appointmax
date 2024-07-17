package com.br.appointmax.domain.service.serviceImpl;


import com.br.appointmax.domain.constants.StringConstants;
import com.br.appointmax.domain.exception.ClientNotFoundException;
import com.br.appointmax.domain.model.Client;
import com.br.appointmax.domain.repository.ClientRepository;
import com.br.appointmax.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.br.appointmax.domain.constants.StringConstants.*;
import static java.lang.String.format;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(Long clientId) {
            return clientRepository.findById(clientId).orElseThrow(() ->
                    new ClientNotFoundException(format(CLIENT_ID_NOT_FOUND, clientId)));
    }

    @Override
    public Client getClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(() ->
                new ClientNotFoundException(format(CLIENT_CPF_NOT_FOUND, cpf)));
    }

    @Override
    public Client getClientByCity(String city) {
        return clientRepository.findByAddress_City(city).orElseThrow(() ->
                new ClientNotFoundException(format(CLIENT_CITY_NOT_FOUND, city)));
    }

    @Override
    public Client getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone).orElseThrow(() ->
                new ClientNotFoundException(format(CLIENT_PHONE_NOT_FOUND, phone)));
    }

}
