package com.projetoFastHub.fasthub.adapters.avaliacao;

import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoModel;

import java.util.List;

public interface AvaliacaoRepository {
    public void inserir(AvaliacaoModel a);
    public List<AvaliacaoModel> listaTudo();

    public void editar(AvaliacaoModel a);

    public AvaliacaoModel buscaPorId(Long id);

    public List<AvaliacaoModel> buscarPorPrestador(Long prestadorId);

    public Double calcularMediaPorPrestador(Long prestadorId);
}
