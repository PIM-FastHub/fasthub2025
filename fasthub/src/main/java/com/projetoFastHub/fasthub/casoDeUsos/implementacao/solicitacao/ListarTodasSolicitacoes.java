package com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDAO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao.ListarSolicitacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodasSolicitacoes implements ListarSolicitacoes {

    @Autowired
    private SolicitacaoDAO dao;
    @Override
    public List<SolicitacaoModel> listaTodasSolicitacoes() {
        return dao.listaTodasSolicitacoes();
    }
}
