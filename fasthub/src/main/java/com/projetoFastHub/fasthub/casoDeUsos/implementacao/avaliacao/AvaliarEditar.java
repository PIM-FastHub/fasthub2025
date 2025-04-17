package com.projetoFastHub.fasthub.casoDeUsos.implementacao.avaliacao;

import com.projetoFastHub.fasthub.adapters.avaliacao.AvaliacaoRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.avaliacao.AvaliacaoModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.avaliacao.AvaliacaoEditarCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliarEditar implements AvaliacaoEditarCase {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;


    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Override
    public String editar(AvaliacaoDTO data, Long id) {
        try{

            AvaliacaoModel avaliacaoModel = avaliacaoRepository.buscaPorId(id);
            avaliacaoModel.setNota(data.nota());
            avaliacaoModel.setComentario(data.comentario());
            SolicitacaoModel solicitacaoModel = solicitacaoRepository.buscaPorID(data.solicitacaoId());
            avaliacaoModel.setSolicitacao(solicitacaoModel);
            avaliacaoModel.setPrestador(solicitacaoModel.getPrestador());
            avaliacaoRepository.editar(avaliacaoModel);

            return "Editado com Sucesso";
        }catch (Exception e){
            return "Erro ao Editar "+ e.getMessage();
        }
    }
}
