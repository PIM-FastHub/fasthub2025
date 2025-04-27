package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico;

import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;

public interface BuscaServicoPorCase {
    ServicoModel porId(Long id);
}
