package com.br.appointmax.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
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

//    @Column(unique = true, nullable = false)
    private String cpf;

    private String name;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "client")
    private List<Message> messages = new ArrayList<>();

}
