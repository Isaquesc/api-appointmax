package com.br.appointmax.domain.constants;

import java.util.regex.Pattern;

public class StringConstants {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    public static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public static final String CSV_UPLOAD_SUCCESS = "Envio do CSV bem-sucedido";
    public static final String CSV_UPLOAD_IO_ERROR = "Falha ao enviar o CSV devido a um erro de E/S";
    public static final String CSV_UPLOAD_VALIDATION_ERROR = "Falha ao enviar o CSV devido a um erro de validação";
    public static final String INVALID_DATA_WARNING = "Dados inválidos na linha: %s";

    public static final String CLIENT_CPF_NOT_FOUND = "Cliente com o CPF %s não foi encontrado. Verifique o número do CPF e tente novamente.";
    public static final String CLIENT_CITY_NOT_FOUND = "Nenhum cliente foi encontrado na cidade %s. Verifique o nome da cidade e tente novamente.";
    public static final String CLIENT_PHONE_NOT_FOUND = "Cliente com o telefone %s não foi encontrado. Verifique o número do telefone e tente novamente.";
    public static final String CLIENT_ID_NOT_FOUND = "Cliente com o ID %d não foi encontrado.";

    public static final String MSG_GENERICO_ERRO_SISTEMA = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o " +
            "problema persistir, entre em contato com o administrador do sistema.";


}
