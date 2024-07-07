package com.br.appointmax.service.serviceImpl;

import com.br.appointmax.service.WhatsAppService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppServiceImpl implements WhatsAppService {

    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth-token}")
    private String AUTH_TOKEN;

    @Value("${twilio.whatsapp-number}")
    private String FROM_NUMBER;

    @Override
    public void sendWhatsAppMessage(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(FROM_NUMBER),
                message
        ).create();
    }
}
