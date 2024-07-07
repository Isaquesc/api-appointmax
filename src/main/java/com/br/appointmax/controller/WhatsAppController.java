package com.br.appointmax.controller;


import com.br.appointmax.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    @Autowired
    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String to, @RequestParam String message) {
        try {
            whatsAppService.sendWhatsAppMessage(to, message);
            return "Message sent successfully to " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send message to " + to;
        }
    }
}
