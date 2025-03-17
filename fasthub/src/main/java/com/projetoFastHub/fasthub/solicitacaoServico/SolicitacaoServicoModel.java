package com.projetoFastHub.fasthub.solicitacaoServico;

import com.projetoFastHub.fasthub.servico.ServicoModel;
import com.projetoFastHub.fasthub.user.User;
import com.projetoFastHub.fasthub.user.UserDTO;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "SOLICITACAOSERVICO")
public class SolicitacaoServicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Calendar dataSolicitacao;
    @ManyToOne
    private ServicoModel servicoId;

    @ManyToOne
    private UserDTO clienteId;

    @ManyToOne
    private UserDTO prestadorId;

    private String prazo;
    private String descricaoDoProblema;


    public SolicitacaoServicoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public ServicoModel getServicoId() {
        return servicoId;
    }

    public void setServicoId(ServicoModel servicoId) {
        this.servicoId = servicoId;
    }

    public UserDTO getClienteId() {
        return clienteId;
    }

    public void setClienteId(UserDTO clienteId) {
        this.clienteId = clienteId;
    }

    public UserDTO getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(UserDTO prestadorId) {
        this.prestadorId = prestadorId;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getDescricaoDoProblema() {
        return descricaoDoProblema;
    }

    public void setDescricaoDoProblema(String descricaoDoProblema) {
        this.descricaoDoProblema = descricaoDoProblema;
    }
}