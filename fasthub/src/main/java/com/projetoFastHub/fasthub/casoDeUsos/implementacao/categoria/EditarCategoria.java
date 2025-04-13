package com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria;


import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.categoria.InclusaoCategoriaDTO;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.categoria.EditarCategoriaCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class EditarCategoria implements EditarCategoriaCase {
@Autowired
    CategoriaDAO dao;


    @Override
    public String editarCategoria(Long id, InclusaoCategoriaDTO data) {
        try {
            CategoriaModel categoria = dao.buscaCategoriaPorId(id);
            categoria.setDataAlteracao(Calendar.getInstance());
            categoria.setDescricao(data.descricao());
            categoria.setDataInclusao(categoria.getDataInclusao());
            dao.alteraCategoria(categoria);
            return "Editado com Sucesso";
        }catch (Exception e){
            return "Erro ao Editar";
        }

    }
}
