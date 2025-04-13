package com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.categoria.InclusaoCategoriaDTO;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.categoria.IncluirCategoriaCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class CriarCategoria implements IncluirCategoriaCase {

    @Autowired
    CategoriaDAO dao;
    @Override
    public String criarCategoria(InclusaoCategoriaDTO dado) {
        try {
            CategoriaModel categoria = new CategoriaModel();
            categoria.setDescricao(dado.descricao());
            categoria.setDataInclusao(Calendar.getInstance());
            dao.insereCategoria(categoria);
            return "Criado com Sucesso";
        }
        catch (Exception e){
            return "Erro ao Criar";
        }
    }
}
