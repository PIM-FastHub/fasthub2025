package com.projetoFastHub.fasthub.adapters.categoria;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;

import java.util.List;

public interface CategoriaRepository {
    void insereCategoria(CategoriaModel categoria);
    void alterarCategoria(CategoriaModel categoriaModel);
    void excluirCategoria(CategoriaModel categoriaModel);
    List<CategoriaModel> listarTodasCategorias();
    CategoriaModel buscaCategoriaPorId(Long id);

}
