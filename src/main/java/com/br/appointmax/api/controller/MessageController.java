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

    @GetMapping("/filter")
    public List<Message> filterMessages(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end,
            @RequestParam(required = false) Long idClient) {

        return messageService.getFilterMessages(idClient, status, start, end);
    }
}

