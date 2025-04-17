package com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao;

import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoHistoricoService;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDTOHistorico;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SolicitacaoHistoricoServiceImp implements SolicitacaoHistoricoService {
    @Override
    public List<SolicitacaoDTOHistorico> listaHistorico(List<SolicitacaoModel> listaModel) {
        List<SolicitacaoDTOHistorico> lista = new ArrayList<>();

        for (SolicitacaoModel solicitacao : listaModel) {
            SolicitacaoDTOHistorico historico = new SolicitacaoDTOHistorico();

            historico.setNomeSolicitacao(solicitacao.getTitulo());
            historico.setDescricao(solicitacao.getDescricao());

            // Protege contra cliente nulo
            if (solicitacao.getCliente() != null) {
                historico.setNomeCliente(solicitacao.getCliente().getNome());
            } else {
                historico.setNomeCliente("Cliente não informado");
            }

            // Protege contra avaliação nula
            if (solicitacao.getAvaliacao() != null) {
                historico.setAvaliacao(solicitacao.getAvaliacao().getNota());
            } else {
                historico.setAvaliacao(0); // ou 0, ou alguma outra regra de fallback
            }

            lista.add(historico);
        }

        return lista;
    }

}
