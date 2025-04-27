package com.projetoFastHub.fasthub.adapters.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;

import java.util.List;

public interface SolicitacaoRepository {
    void insere (SolicitacaoModel s);
    void altera(SolicitacaoModel s);
    void excluir(SolicitacaoModel s);
    List<SolicitacaoModel> listaTodasSolicitacoes();
    SolicitacaoModel buscaPorID(Long id);
    List<SolicitacaoModel> listaSolicitacaoPorCategoria(CategoriaModel categoriaModel);
}
