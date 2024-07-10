package com.br.appointmax.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    private String cpf;
    private String name;
    private String sobrenome;
    private String phone;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Message> messages = new ArrayList<>();

}
