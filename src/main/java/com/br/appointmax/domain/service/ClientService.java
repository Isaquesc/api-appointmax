package com.br.appointmax.domain.service;

import com.br.appointmax.domain.model.Client;

public interface ClientService {

    Client getClient(Long clientId);
    Client getClientByCpf(String cpf);
    Client getClientByCity(String city);
    Client getClientByPhone(String phone);
}
