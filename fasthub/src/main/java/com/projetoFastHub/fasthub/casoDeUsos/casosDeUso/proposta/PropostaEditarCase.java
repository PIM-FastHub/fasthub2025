package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.proposta;

import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaEditarDTO;

public interface PropostaEditarCase {
    String editar(PropostaEditarDTO dto, Long id);
}
