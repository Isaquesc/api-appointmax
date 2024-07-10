package com.br.appointmax.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

}
