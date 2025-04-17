package com.projetoFastHub.fasthub.aplicacao.proposta;

import jakarta.validation.constraints.NotNull;

public class PropostaEditarDTO {
    @NotNull
    private double valor;
    @NotNull
    private String descricao;

    private PropostaStatusEnum propostaStatusEnum;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PropostaStatusEnum getPropostaStatusEnum() {
        return propostaStatusEnum;
    }

    public void setPropostaStatusEnum(PropostaStatusEnum propostaStatusEnum) {
        this.propostaStatusEnum = propostaStatusEnum;
    }
}
