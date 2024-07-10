package com.br.appointmax.domain.service.serviceImpl;

import com.br.appointmax.domain.model.Client;
import com.br.appointmax.domain.model.Message;
import com.br.appointmax.domain.repository.MessageRepository;
import com.br.appointmax.domain.service.ClientService;
import com.br.appointmax.domain.service.MessagePersonalizationService;
import com.br.appointmax.domain.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.br.appointmax.domain.Enum.StatusEnum.STATUS_PENDING;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessagePersonalizationService personalizeMessage;

    @Autowired
    private ClientService clientService;

    //TODO add template kafka

    //    @Transactional
    @Override
    public Message createMessage(Long clientId, String messageContent) {
        Client client = clientService.getClient(clientId);
        var personalizedMessage = personalizeMessage.personalizeMessage(messageContent, client);

        return constructorMessage(client, personalizedMessage);
        //        kafkaTemplate.send("messages", messageContent);
    }

    private Message constructorMessage(Client client, String personalizedMessage) {
        Message message = new Message();
        message.setClient(client);
        message.setContent(personalizedMessage);
        message.setStatus(STATUS_PENDING.getStatus());
        message.setDataMessage(LocalDateTime.now());
        return message;
    }

    public List<Message> getMessagesByClientId(Long clientId) {
        return messageRepository.findByClientId(clientId);
    }

    public List<Message> getMessagesByClientIdAndStatus(Long clientId, String status) {
        return messageRepository.findByClientIdAndStatus(clientId, status);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getMessagesByStatus(String status) {
        return messageRepository.findByStatus(status);
    }

    public List<Message> getMessagesByDateRange(LocalDateTime start, LocalDateTime end) {
        return messageRepository.findByDataMessageBetween(start, end);
    }

    public List<Message> getMessageByStatusAndDateRange(String status, LocalDateTime start, LocalDateTime end) {
        return messageRepository.findByStatusAndDataMessageBetween(status, start, end);
    }

    public List<Message> getFilteredMessages(String status, LocalDateTime start, LocalDateTime end){
        if (StringUtils.hasLength(status) && start != null && end != null) {
            return getMessageByStatusAndDateRange(status, start, end);
        } else if (StringUtils.hasLength(status)) {
            return getMessagesByStatus(status);
        } else if (start != null && end != null) {
            return getMessagesByDateRange(start, end);
        } else if (!StringUtils.hasLength(status) && start == null && end == null) {
            return getAllMessages();
        } else {
            throw new IllegalArgumentException("Status and start and end are both null");
        }
    }

}
