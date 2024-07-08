package com.br.appointmax.service;

public interface MessageService {

    String sendMessage(Long clientId, String messageContent);
}