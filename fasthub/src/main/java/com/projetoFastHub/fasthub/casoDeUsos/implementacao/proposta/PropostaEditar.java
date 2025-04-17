package com.projetoFastHub.fasthub.casoDeUsos.implementacao.proposta;

import com.projetoFastHub.fasthub.adapters.proposta.PropostaRepository;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaEditarDTO;
import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaModel;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.proposta.PropostaEditarCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class PropostaEditar implements PropostaEditarCase {

    @Autowired
    PropostaRepository repository;

    @Override
    public String editar(PropostaEditarDTO dto, Long id) {
        try {
            PropostaModel propostaModel = repository.buscarPorId(id);
            propostaModel.setValorProposta(dto.getValor());
            propostaModel.setDescricao(dto.getDescricao());
            propostaModel.setDataAlteracao(Calendar.getInstance());
            if(dto.getPropostaStatusEnum() != null){
                propostaModel.setStatusPropostaEnum(dto.getPropostaStatusEnum());
            }

            repository.editar(propostaModel);


            return "Editado com sucesso";
        }catch (Exception e){
            return "Erro ao editar "+ e.getMessage();
        }

    }
}
