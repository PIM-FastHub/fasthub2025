package com.projetoFastHub.fasthub.casoDeUsos.implementacao.proposta;

import com.projetoFastHub.fasthub.adapters.proposta.PropostaRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaCriarDTO;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaModel;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaStatusEnum;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.proposta.PropostaCriarCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class PropostaCriar implements PropostaCriarCase {

    @Autowired
    PropostaRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Override
    public String criarProposta(PropostaCriarDTO data) {
        try{
            PropostaModel propostaModel = new PropostaModel();
            propostaModel.setValorProposta(data.getValor());
            propostaModel.setStatusPropostaEnum(PropostaStatusEnum.AGUARDANDO_RESPOSTA);
            propostaModel.setDataInclusao(Calendar.getInstance());
            propostaModel.setSolicitacaoModel(solicitacaoRepository.buscaPorID(data.getSolicitacaoId()));
            propostaModel.setPrestador(userRepository.findById(data.getIdPrestador()).orElse(null));

            repository.salvar(propostaModel);
            return "Proposta Enviada";
        }catch (Exception e){
            return "Erro ao enviar proposta "+ e.getMessage();
        }


    }
}
