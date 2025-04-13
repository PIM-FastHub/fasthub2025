package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;

import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.BuscaServicoPorCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BuscaServicoPor implements BuscaServicoPorCase {
    @Autowired
    ServicoDAO dao;
    @Override
    public ServicoModel porId(Long id) {
       ServicoModel servico = dao.buscaServicoPorId(id);
       return servico;
    }
}
