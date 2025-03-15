package com.projetoFastHub.fasthub.servico;

import jakarta.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "SERVICO")
public class ServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Calendar dataInclusao;
    private Calendar dataAlteracao;

    // Construtor privado para evitar instanciação direta
    ServicoModel(ServicoBuilder builder) {
        this.nome = builder.getNome();
        this.descricao = builder.getDescricao();
        this.dataInclusao = builder.getDataInclusao();
        this.dataAlteracao = builder.getDataAlteracao();
    }

    public Long getId() {
        return id;
    }

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

    // Método para acessar o Builder
    public static ServicoBuilder builder() {
        return new ServicoBuilder();
    }

    @Override
    public String toString() {
        return "ServicoModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInclusao=" + dataInclusao.getTime() +
                ", dataAlteracao=" + dataAlteracao.getTime() +
                '}';
    }
}