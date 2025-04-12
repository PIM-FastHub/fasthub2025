package com.projetoFastHub.fasthub.aplicacao.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import jakarta.persistence.*;
import java.util.Calendar;

@Entity
public class SolicitacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServicoModel servico;
    @ManyToOne
    private CategoriaModel categoria;
    @ManyToOne
    private User cliente;
    @ManyToOne
    private User prestador;
    private Calendar dataCriacao;
    private String prazo;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    SolicitacaoModel() {}

    public Long getId() {
        return id;
    }

    public SolicitacaoModel(ServicoModel servico, CategoriaModel categoria, User cliente, User prestador, Calendar dataCriacao, String prazo, StatusEnum status) {
        this.servico = servico;
        this.categoria = categoria;
        this.cliente = cliente;
        this.prestador = prestador;
        this.dataCriacao = dataCriacao;
        this.prazo = prazo;
        this.status = status;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public ServicoModel getServico() {
        return servico;
    }

    public void setServico(ServicoModel servico) {
        this.servico = servico;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public User getCliente() {
        return cliente;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }

    public User getPrestador() {
        return prestador;
    }

    public void setPrestador(User prestador) {
        this.prestador = prestador;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
