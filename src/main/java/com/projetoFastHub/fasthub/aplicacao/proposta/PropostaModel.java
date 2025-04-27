package com.projetoFastHub.fasthub.aplicacao.proposta;


import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
public class PropostaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valorProposta;
    private String descricao;

    private String retornoCliente;
    private PropostaStatusEnum statusPropostaEnum;

    @ManyToOne
    private SolicitacaoModel solicitacaoModel;

    private Calendar dataInclusao;
    private Calendar dataAlteracao;

    @ManyToOne
    private User prestador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorProposta() {
        return valorProposta;
    }

    public void setValorProposta(double valorProposta) {
        this.valorProposta = valorProposta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRetornoCliente() {
        return retornoCliente;
    }

    public void setRetornoCliente(String retornoCliente) {
        this.retornoCliente = retornoCliente;
    }

    public PropostaStatusEnum getStatusPropostaEnum() {
        return statusPropostaEnum;
    }

    public void setStatusPropostaEnum(PropostaStatusEnum statusPropostaEnum) {
        this.statusPropostaEnum = statusPropostaEnum;
    }

    public SolicitacaoModel getSolicitacaoModel() {
        return solicitacaoModel;
    }

    public void setSolicitacaoModel(SolicitacaoModel solicitacaoModel) {
        this.solicitacaoModel = solicitacaoModel;
    }

    public Calendar getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Calendar dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Calendar getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Calendar dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public User getPrestador() {
        return prestador;
    }

    public void setPrestador(User prestador) {
        this.prestador = prestador;
    }
}
