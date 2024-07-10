package com.br.appointmax.domain.Enum;

public enum StatusEnum {

    STATUS_PENDING("PENDING"),
    STATUS_SENT("SUCCESS"),
    STATUS_FAILED("FAILED");

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
