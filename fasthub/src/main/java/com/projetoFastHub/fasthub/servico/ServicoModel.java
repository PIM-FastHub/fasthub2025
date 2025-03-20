package com.projetoFastHub.fasthub.servico;

import com.projetoFastHub.fasthub.categoria.CategoriaModel;
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

    @ManyToOne
    private CategoriaModel categoria;

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    // Construtor privado para evitar instanciação direta


    public ServicoModel(){}


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataInclusao(Calendar dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public void setDataAlteracao(Calendar dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return "ServicoModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInclusao=" + (dataInclusao != null ? dataInclusao.getTime() : "null") +
                ", dataAlteracao=" + (dataAlteracao != null ? dataAlteracao.getTime() : "null") +
                ", categoria=" + (categoria != null ? categoria.getDescricao() : "null") +
                '}';
    }

}