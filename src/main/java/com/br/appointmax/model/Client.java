package com.br.appointmax.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

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

    @Column(unique = true)
    private String phone;

    private String email;
    private String message;
    private String status;
    private Timestamp timestamp;
}
