package com.br.appointmax.api.controller;

import com.br.appointmax.domain.model.Message;
import com.br.appointmax.domain.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/client/{clientId}")
    public List<Message> getMessageById(@PathVariable Long clientId) {
        return messageService.getMessagesByClientId(clientId);
    }

    @GetMapping("/client/{clientId}/status/{status}")
    public List<Message> getMessagesByClientIdAndStatus(@PathVariable Long clientId, @PathVariable String status) {
        return messageService.getMessagesByClientIdAndStatus(clientId, status);
    }

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/filter")
    public List<Message> filterMessages(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        return messageService.getFilteredMessages(status, start, end);
    }
}

