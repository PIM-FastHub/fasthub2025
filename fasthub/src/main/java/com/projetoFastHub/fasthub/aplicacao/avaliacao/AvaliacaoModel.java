package com.projetoFastHub.fasthub.aplicacao.avaliacao;


import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import jakarta.persistence.*;

@Entity
public class AvaliacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double nota;
    private String comentario;


    @ManyToOne
    private User prestador;

    @OneToOne
    private SolicitacaoModel solicitacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public User getPrestador() {
        return prestador;
    }

    public void setPrestador(User prestador) {
        this.prestador = prestador;
    }

    public SolicitacaoModel getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoModel solicitacao) {
        this.solicitacao = solicitacao;
    }
}
