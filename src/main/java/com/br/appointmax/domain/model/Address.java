package com.br.appointmax.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "address_cep")
    private String cep;

    @Column(name = "address_logradouro")
    private String logradouro;

    @Column(name = "address_numero")
    private String number;

    @Column(name = "address_complemento")
    private String complemento;

    @Column(name = "address_bairro")
    private String neighborhood;

    @Column(name = "address_cidade")
    private String city;

}
