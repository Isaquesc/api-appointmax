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

    @GetMapping("/filter/{id}")
    public List<Message> filterMessagesClientId(
            @PathVariable(name = "id", required = false) Long selectedUserId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        return messageService.getFilterMessages(selectedUserId, status, start, end);
    }

    @GetMapping("/filter")
    public List<Message> filterMessages(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) LocalDateTime start,
        @RequestParam(required = false) LocalDateTime end){
        return messageService.getFilterMessages(null, status, start, end);
    }
}