package com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.categoria.ExcluirCategoriaCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExcluirCategoria implements ExcluirCategoriaCase {

    @Autowired
    CategoriaDAO dao;
    @Override
    public String excluirCategoria(Long id) {
        try {
            CategoriaModel categoria = dao.buscaCategoriaPorId(id);
            dao.excluirCategoria(categoria);
            return "Excluído com Sucesso";
        }catch (Exception e){
            return "Erro no excluir categoria "+ e.getMessage();
        }

    }
}
