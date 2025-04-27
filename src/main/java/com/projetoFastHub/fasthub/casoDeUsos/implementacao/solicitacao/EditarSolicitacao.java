package com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDAO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoFactory;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoModel;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoResponseDTO;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao.EditarSolicitacaoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditarSolicitacao implements EditarSolicitacaoCase {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    SolicitacaoFactory factory;

    @Autowired
    UserRepository repository;

    @Autowired
    ServicoRepository servicoRepository;
    @Override
    public String editarSolicitacao(Long id, SolicitacaoResponseDTO data) {
        try {
            SolicitacaoModel solicitacaoModel = solicitacaoRepository.buscaPorID(id);
            solicitacaoModel.setCategoria(categoriaRepository.buscaCategoriaPorId(data.idCategoria()));
            solicitacaoModel.setPrazo(data.prazo());
            solicitacaoModel.setCliente(repository.getReferenceById(data.idCliente()));
            solicitacaoModel.setServico(servicoRepository.buscaServicoPorId(data.idServico()));

            solicitacaoRepository.altera(solicitacaoModel);
            return "Alterou Com Sucesso";
        }catch (Exception e){
            return "erro "+ e.getMessage();
        }
    }
}
