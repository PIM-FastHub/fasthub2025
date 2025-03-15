package com.projetoFastHub.fasthub.servico;

import java.util.Calendar;

public class ServicoBuilder {
    private String nome;
    private String descricao;
    private Calendar dataInclusao;
    private Calendar dataAlteracao;

    public ServicoBuilder nome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome Inválido");
        }
        this.nome = nome;
        return this;
    }

    public ServicoBuilder descricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição Inválida");
        }
        this.descricao = descricao;
        return this;
    }

    public ServicoBuilder dataInclusao(Calendar dataInclusao) {
        this.dataInclusao = dataInclusao;
        return this;
    }

    public ServicoBuilder dataAlteracao(Calendar dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
        return this;
    }

    public ServicoModel build() {
        return new ServicoModel(this);
    }

    // Getters para o ServicoModel acessar os valores
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Calendar getDataInclusao() {
        return dataInclusao;
    }

    public Calendar getDataAlteracao() {
        return dataAlteracao;
    }
}
