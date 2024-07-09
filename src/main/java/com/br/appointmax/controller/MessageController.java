package com.br.appointmax.controller;

import com.br.appointmax.model.Message;
import com.br.appointmax.model.dto.request.FilterMessageRequestDTO;
import com.br.appointmax.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/status/{status}")
    public List<Message> getMessagesByStatus(@PathVariable String status) {
        return messageService.getMessagesByStatus(status);
    }

    @GetMapping("/client/{clientId}/status/{status}")
    public List<Message> getMessagesByClientIdAndStatus(@PathVariable Long clientId, @PathVariable String status) {
        return messageService.getMessagesByClientIdAndStatus(clientId, status);
    }

    @GetMapping("/dateRange")
    public List<Message> getMessagesByDateRange(@RequestBody FilterMessageRequestDTO filter) {
        return messageService.getMessagesByDateRange(filter.start(), filter.end());
    }

    @GetMapping( "/filter")
    public List<Message> getMessageByStatusAndDate(@RequestBody FilterMessageRequestDTO filter) {
        return messageService.getMessageByStatusAndDateRange(filter.status(), filter.start(), filter.end());
    }
}