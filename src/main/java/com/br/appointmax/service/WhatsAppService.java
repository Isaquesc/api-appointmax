package com.br.appointmax.service;

import com.br.appointmax.model.Client;
import com.br.appointmax.model.Message;

public interface WhatsAppService {

    void sendWhatsAppMessage(Long client, String mensagem);
}
