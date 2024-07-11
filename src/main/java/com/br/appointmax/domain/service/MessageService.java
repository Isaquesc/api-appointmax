package com.br.appointmax.domain.service;

import com.br.appointmax.domain.model.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {

    Message createMessage(Long clientId, String messageContent);

    List<Message> getMessagesByClientId(Long clientId);

    List<Message> getMessagesByClientIdAndStatus(Long clientId, String status);

    List<Message> getAllMessages();

    List<Message> getFilterMessages(String status, LocalDateTime start, LocalDateTime end);

}