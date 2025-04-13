package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoResponseDTO;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.EditarServicoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class EditarServico implements EditarServicoCase {

    @Autowired
    ServicoDAO dao;

    @Autowired
    CategoriaDAO categoriaDAO;
    @Override
    public String editarServico(Long id, ServicoResponseDTO data) {

        try {
            ServicoModel servico = dao.buscaServicoPorId(id);
            servico.setCategoria(categoriaDAO.buscaCategoriaPorId(data.categoriaId()));
            servico.setDataAlteracao(Calendar.getInstance());
            servico.setNome(data.nome());
            servico.setDescricao(data.descricao());
            dao.alteraServico(servico);
            return "Alterado com Sucesso";
        }catch (Exception e){
            return "Erro ao Editar "+ e.getMessage();
        }

    }
}
