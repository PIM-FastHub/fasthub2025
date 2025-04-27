package com.projetoFastHub.fasthub.aplicacao.solicitacao;

import org.springframework.stereotype.Component;

@Component
public class SolicitacaoFactory {

    public SolicitacaoModel deDTO_Para_Model(SolicitacaoDTO dto) {
        if (dto == null) {
            return null;
        }

        SolicitacaoModel model = new SolicitacaoModel();
        model.setServico(dto.servicoModel());
        model.setCategoria(dto.categoriaModel());
        model.setCliente(dto.cliente());
        model.setDataCriacao(dto.dataCriacao());
        model.setPrazo(dto.prazo());
        model.setStatus(dto.status());

        return model;
    }
}
