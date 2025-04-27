package com.projetoFastHub.fasthub.aplicacao.categoria;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Calendar dataInclusao;
    private Calendar dataAlteracao;
    private String descricao;

    public CategoriaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
