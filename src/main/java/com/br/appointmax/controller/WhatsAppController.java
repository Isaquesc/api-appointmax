package com.br.appointmax.controller;

import com.br.appointmax.model.dto.request.SendMessageRequestDTO;
import com.br.appointmax.service.MessageService;
import com.br.appointmax.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequestDTO request) {

        var clientIds = request.clientIds();
        var messageContent = request.messageContent();

        for (Long client : clientIds) {
            whatsAppService.sendWhatsAppMessage(client, messageContent);
        }

        return ResponseEntity.noContent().build();
    }
}
