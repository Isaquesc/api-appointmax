package com.br.appointmax.service;

import com.br.appointmax.model.Client;

import java.util.Optional;

public interface ClientService {

    Client getClient(Long clientId);
    Client getClientByCpf(String cpf);
    Client getClientByCity(String city);
    Client getClientByPhone(String phone);
}
