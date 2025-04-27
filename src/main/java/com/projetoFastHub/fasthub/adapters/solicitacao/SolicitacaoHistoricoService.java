package com.projetoFastHub.fasthub.adapters.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDTOHistorico;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;

import java.util.List;

public interface SolicitacaoHistoricoService {
    List<SolicitacaoDTOHistorico> listaHistorico(List<SolicitacaoModel> listaModel);
}
