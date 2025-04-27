package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoResponseDTO;

public interface CriarSolicitacaoCase {

    SolicitacaoDTO criarSolicitacao(SolicitacaoResponseDTO data);
}
