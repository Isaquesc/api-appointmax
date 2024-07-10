package com.br.appointmax.domain.service;

import com.br.appointmax.domain.model.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {

    Message createMessage(Long clientId, String messageContent);

    List<Message> getMessagesByClientId(Long clientId);

    List<Message> getMessagesByClientIdAndStatus(Long clientId, String status);

    List<Message> getAllMessages();

    List<Message> getMessagesByStatus(String status);

    List<Message> getMessagesByDateRange(LocalDateTime start, LocalDateTime end);

    List<Message> getMessageByStatusAndDateRange(String status, LocalDateTime start, LocalDateTime end);

    List<Message> getFilteredMessages(String status, LocalDateTime start, LocalDateTime end);

}