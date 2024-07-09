package com.br.appointmax.service.serviceImpl;

import com.br.appointmax.model.Client;
import com.br.appointmax.service.MessagePersonalizationService;
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
