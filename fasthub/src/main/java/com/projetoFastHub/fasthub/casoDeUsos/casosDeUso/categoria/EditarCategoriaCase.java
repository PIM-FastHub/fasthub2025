package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.categoria;

import com.projetoFastHub.fasthub.aplicacao.categoria.InclusaoCategoriaDTO;

public interface EditarCategoriaCase {
    String editarCategoria(Long id, InclusaoCategoriaDTO data);
}