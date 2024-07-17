package com.br.appointmax.domain.exception;

public class ClientNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String message) {
        super(message);
    }

}
