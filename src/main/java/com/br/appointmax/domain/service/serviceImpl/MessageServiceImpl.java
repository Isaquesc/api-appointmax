package com.br.appointmax.domain.service.serviceImpl;

import com.br.appointmax.domain.model.Client;
import com.br.appointmax.domain.model.Message;
import com.br.appointmax.domain.repository.MessageRepository;
import com.br.appointmax.domain.service.ClientService;
import com.br.appointmax.domain.service.MessagePersonalizationService;
import com.br.appointmax.domain.service.MessageService;
import com.br.appointmax.infrastucture.spec.MessageSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.br.appointmax.domain.Enum.StatusEnum.STATUS_PENDING;
import static com.br.appointmax.infrastucture.spec.MessageSpecification.filterStatusDateStartEnd;

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

    public List<Message> getFilterMessages(Long idClient,
                                           String status,
                                           LocalDateTime start,
                                           LocalDateTime end) {
        return messageRepository.
                findAll(filterStatusDateStartEnd(idClient, status, start, end));
    }

}
