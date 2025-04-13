package com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.categoria.ListaCategoriaCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarCategoria implements ListaCategoriaCase {

   @Autowired
    CategoriaDAO dao;
    @Override
    public List<CategoriaModel> listaTodasCategorias() {
            List<CategoriaModel> lista = dao.listaTodasCategorias();
            return lista;

    }
}
