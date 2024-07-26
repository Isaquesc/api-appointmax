package com.br.appointmax.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro invalido"),
    ERRO_DE_SISTEMA("/erro-sistema","Erro de sistema" ),
    CSV_ERRO_UPLOAD("/erro-uploud-csv", "Erro no upload do CSV");

    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "https://appointmax.com.br/problem" + path;
        this.title = title;
    }
}
