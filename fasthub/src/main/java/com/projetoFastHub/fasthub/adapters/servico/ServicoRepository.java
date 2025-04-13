package com.projetoFastHub.fasthub.adapters.servico;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;

import java.util.List;

public interface ServicoRepository {
    void inserirServico(ServicoModel servicoModel);
    void alteraServico(ServicoModel servicoModel);
    void excluirServico(ServicoModel servicoModel);
    List<ServicoModel> listaTodosServicos();
    ServicoModel buscaServicoPorId(Long id);
    List<ServicoModel> buscaServicoPorCategoria(CategoriaModel categoria);

}
