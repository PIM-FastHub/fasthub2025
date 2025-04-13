package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;

import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.ListarServicoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarServico implements ListarServicoCase {

    @Autowired
    ServicoDAO dao;
    @Override
    public List<ServicoModel> listaTodos() {
       return dao.listaTodosServicos();
    }
}
