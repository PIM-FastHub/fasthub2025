package com.projetoFastHub.fasthub.aplicacao.proposta;

import jakarta.validation.constraints.NotNull;

public class PropostaCriarDTO {
    @NotNull
    private double valor;
    @NotNull
    private String descricao;

    @NotNull
    private Long solicitacaoId;

    @NotNull
    private Long idPrestador;

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

    public Long getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(Long solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public Long getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Long idPrestador) {
        this.idPrestador = idPrestador;
    }
}
