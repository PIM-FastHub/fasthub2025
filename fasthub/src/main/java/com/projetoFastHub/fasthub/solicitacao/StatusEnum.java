package com.projetoFastHub.fasthub.solicitacao;

public enum StatusEnum {
    SEM_ATENDIMENTO("Sem Atendimento"),
    EM_ATENDIMENTO("Em Atendimento"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");


    private final String descricao;
    StatusEnum(String descricao){
        this.descricao = descricao;
    }
}
