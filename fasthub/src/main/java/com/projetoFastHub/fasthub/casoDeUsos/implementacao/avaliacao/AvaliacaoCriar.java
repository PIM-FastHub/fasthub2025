package com.projetoFastHub.fasthub.casoDeUsos.implementacao.avaliacao;

import com.projetoFastHub.fasthub.adapters.avaliacao.AvaliacaoRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.avaliacao.AvaliacaoCriarCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AvaliacaoCriar implements AvaliacaoCriarCase {

    @Autowired
    AvaliacaoRepository repository;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;


    @Override
    public String avaliar(AvaliacaoDTO data) {
        try {
            AvaliacaoModel a = new AvaliacaoModel();
            a.setComentario(data.comentario());
            a.setNota(data.nota());

            SolicitacaoModel solicitacao = solicitacaoRepository.buscaPorID(data.solicitacaoId());
            a.setSolicitacao(solicitacao);
            a.setPrestador(solicitacao.getPrestador());

            // salva primeiro a avaliação
            repository.inserir(a);

            // agora associa a avaliação à solicitação
            solicitacao.setAvaliacao(a);
            solicitacaoRepository.altera(solicitacao);

            return "Avaliado Com Sucesso";
        } catch (Exception e) {
            return "Erro ao Avaliar: " + e.getMessage();
        }
    }
}
