package com.br.appointmax.service;

import com.br.appointmax.model.Client;

public interface MessagePersonalizationService {

    String personalizeMessage(String messageContent, Client client);

}
