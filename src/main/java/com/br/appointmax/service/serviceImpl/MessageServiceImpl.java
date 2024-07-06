package com.br.appointmax.service.serviceImpl;

import com.br.appointmax.model.Client;
import com.br.appointmax.model.Message;
import com.br.appointmax.repository.ClientRepository;
import com.br.appointmax.repository.MessageRepository;
import com.br.appointmax.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageRepository messageRepository;

    //TODO add template kafka

    public void sendMessage(Long clientId, String messageContent) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        //TODO ajustar parametros de mensagem
        Message message = new Message();
        message.setClient(client);
        message.setContent(messageContent);
        message.setStatus("PENDING");
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageRepository.save(message);
        //kafkaTemplate.send("messages", messageContent);
    }

}
