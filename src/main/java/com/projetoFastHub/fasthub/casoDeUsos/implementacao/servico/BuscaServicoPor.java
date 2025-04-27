package com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico;

import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.BuscaServicoPorCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BuscaServicoPor implements BuscaServicoPorCase {
    @Autowired
    ServicoRepository servicoRepository;
    @Override
    public ServicoModel porId(Long id) {
       ServicoModel servico = servicoRepository.buscaServicoPorId(id);
       return servico;
    }
}
