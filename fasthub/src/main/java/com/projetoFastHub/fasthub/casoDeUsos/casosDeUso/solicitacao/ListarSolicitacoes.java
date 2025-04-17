package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;

import java.util.List;


public interface ListarSolicitacoes {
    List<SolicitacaoModel> listaTodasSolicitacoes();
    List<SolicitacaoModel> listaParaHistorico();
}
