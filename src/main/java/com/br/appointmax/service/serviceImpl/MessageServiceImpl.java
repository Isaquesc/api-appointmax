package com.br.appointmax.service.serviceImpl;

import com.br.appointmax.model.Client;
import com.br.appointmax.model.Message;
import com.br.appointmax.repository.ClientRepository;
import com.br.appointmax.repository.MessageRepository;
import com.br.appointmax.service.MessageService;
import com.br.appointmax.service.WhatsAppService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private static final String STATUS_PENDING = "PENDING";
    private static final String STATUS_SENT = "SUCESS";
    private static final String STATUS_FAILED = "FAILED";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private WhatsAppService whatsAppService;

    //TODO add template kafka

//    @Transactional
    @Override
    public String sendMessage(Long clientId, String messageContent) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        String personalizedMessage = personalizeMessage(messageContent, client);

        //TODO ajustar parametros de mensagem
        Message message = new Message();
        message.setClient(client);
        message.setContent(personalizedMessage);
        message.setStatus(STATUS_PENDING);
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageRepository.save(message);

        try {
            whatsAppService.sendWhatsAppMessage("+55" + client.getPhone(), message.getContent());
            message.setStatus(STATUS_SENT);
            messageRepository.save(message);
            logger.info("Message sent successfully to {}", client.getPhone());

            return "Message sent successfully to " + client.getPhone();
        } catch (Exception e) {
            message.setStatus(STATUS_FAILED);
            messageRepository.save(message);
            logger.error("Failed to send message to {}", client.getPhone(), e);

            return "Failed to send message to " + client.getPhone();
        }

        //        kafkaTemplate.send("messages", messageContent);
    }

    private String personalizeMessage(String messageContent, Client client) {
        // Implementação básica de substituição de variáveis
        String personalizedMessage = messageContent
                .replace("{nome}", client.getName());

        return personalizedMessage;
    }

}
