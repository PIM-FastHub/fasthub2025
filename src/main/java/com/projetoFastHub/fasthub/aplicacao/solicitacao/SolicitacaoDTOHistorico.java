package com.projetoFastHub.fasthub.aplicacao.solicitacao;

import lombok.Data;

@Data
public class SolicitacaoDTOHistorico {
    private String nomeSolicitacao;
    private String nomeCliente;
    private String descricao;
    private double avaliacao;

    public String getNomeSolicitacao() {
        return nomeSolicitacao;
    }

    public void setNomeSolicitacao(String nomeSolicitacao) {
        this.nomeSolicitacao = nomeSolicitacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
