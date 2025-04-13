package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoResponseDTO;

public interface EditarSolicitacaoCase {
    String editarSolicitacao(Long id, SolicitacaoResponseDTO data);
}
