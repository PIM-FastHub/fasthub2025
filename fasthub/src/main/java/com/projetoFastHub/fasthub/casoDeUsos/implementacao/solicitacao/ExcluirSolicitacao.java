package com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao;

import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDAO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao.ExcluirSolicitacaoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExcluirSolicitacao implements ExcluirSolicitacaoCase {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Override
    public String excluirSolicitacao(Long id) {
        try {
            SolicitacaoModel solicitacao = solicitacaoRepository.buscaPorID(id);
            solicitacaoRepository.excluir(solicitacao);
            return "Excluido com Sucesso";
        }catch(Exception e){

            return "Erro ao excluir";
        }
    }
}
