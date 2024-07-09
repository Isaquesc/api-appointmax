package com.br.appointmax.service;

import com.br.appointmax.model.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {

    Message createMessage(Long clientId, String messageContent);

    List<Message> getMessagesByClientId(Long clientId);

    List<Message> getMessagesByStatus(String status);

    List<Message> getMessagesByClientIdAndStatus(Long clientId, String status);

    List<Message> getMessagesByDateRange(LocalDateTime start, LocalDateTime end);

    List<Message> getMessageByStatusAndDateRange(String status, LocalDateTime start, LocalDateTime end);

}