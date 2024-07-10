package com.br.appointmax.domain.service.serviceImpl;

import com.br.appointmax.domain.model.Client;
import com.br.appointmax.domain.service.MessagePersonalizationService;
import org.springframework.stereotype.Service;

@Service
public class MessagePersonalizationServiceImpl implements MessagePersonalizationService {

    @Override
    public String personalizeMessage(String messageContent, Client client) {
        return messageContent.replace("{nome}", primeiraLetraMaiuscula(client.getName()));
    }

    private String primeiraLetraMaiuscula(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
