package com.br.appointmax.service.serviceImpl;

import com.br.appointmax.repository.MessageRepository;
import com.br.appointmax.service.MessageService;
import com.br.appointmax.service.WhatsAppService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.br.appointmax.Enum.StatusEnum.STATUS_FAILED;
import static com.br.appointmax.Enum.StatusEnum.STATUS_SENT;

@Service
public class WhatsAppServiceImpl implements WhatsAppService {

    private static final Logger logger = LoggerFactory.getLogger(WhatsAppServiceImpl.class);

    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth-token}")
    private String AUTH_TOKEN;

    @Value("${twilio.whatsapp-number}")
    private String FROM_NUMBER;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public void sendWhatsAppMessage(Long clientId, String mensagem) {
        var message = messageService.createMessage(clientId, mensagem);
        messageRepository.save(message);

        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(
                    new PhoneNumber("whatsapp:+55" + message.getClient().getPhone()),
                    new PhoneNumber(FROM_NUMBER),
                    message.getContent()
            ).create();

            message.setStatus(STATUS_SENT.getStatus());
            messageRepository.save(message);
            logger.info("Message sent successfully to {} - {}", message.getClient().getPhone(), message.getClient().getName());
        } catch (Exception e) {
            message.setStatus(STATUS_FAILED.getStatus());
            messageRepository.save(message);
            logger.error("Failed to send message to {}", message.getClient().getPhone(), e);
        }
    }
}
