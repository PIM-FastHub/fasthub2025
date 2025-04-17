package com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao;

import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoHistoricoService;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDTOHistorico;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao.ListarSolicitacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodasSolicitacoes implements ListarSolicitacoes {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Autowired
    SolicitacaoHistoricoService solicitacaoHistoricoService;

    @Override
    public List<SolicitacaoModel> listaTodasSolicitacoes() {
        return solicitacaoRepository.listaTodasSolicitacoes();
    }

    @Override
    public List<SolicitacaoModel> listaParaHistorico() {
      List<SolicitacaoModel> lista = listaTodasSolicitacoes();
      List<SolicitacaoDTOHistorico> listaDTOs = solicitacaoHistoricoService.listaHistorico(lista);

        return lista;
    }
}
