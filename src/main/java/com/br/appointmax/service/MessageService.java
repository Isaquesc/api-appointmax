package com.br.appointmax.service;

public interface MessageService {
    void sendMessage(Long clienteId, String messageContent);
}