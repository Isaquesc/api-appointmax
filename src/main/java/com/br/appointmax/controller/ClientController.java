package com.br.appointmax.controller;

import com.br.appointmax.model.Client;
import com.br.appointmax.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<Client> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(clientService.getClientByCpf(cpf));
    }

    @GetMapping("city/{city}")
    public ResponseEntity<Client> findByCity(@PathVariable String city){
        return ResponseEntity.ok(clientService.getClientByCity(city));
    }

    @GetMapping("phone/{phone}")
    public ResponseEntity<Client> findByPhone(@PathVariable String phone){
        return ResponseEntity.ok(clientService.getClientByPhone(phone));
    }
}
