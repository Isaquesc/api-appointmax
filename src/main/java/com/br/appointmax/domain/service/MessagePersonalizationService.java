package com.br.appointmax.domain.service;

import com.br.appointmax.domain.model.Client;

public interface MessagePersonalizationService {

    String personalizeMessage(String messageContent, Client client);

}
