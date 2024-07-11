package com.br.appointmax.domain.service;

import com.br.appointmax.domain.model.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {

    Message createMessage(Long clientId, String messageContent);

    List<Message> getFilterMessages(Long idClient, String status, LocalDateTime start, LocalDateTime end);

}