package com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao;

import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoDTO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.SolicitacaoResponseDTO;
import com.projetoFastHub.fasthub.aplicacao.solicitacao.StatusEnum;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.solicitacao.CriarSolicitacaoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;




@Component
public class CriarSolicitacao implements CriarSolicitacaoCase {
    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public SolicitacaoDTO criarSolicitacao(SolicitacaoResponseDTO data) {
        User usuario = userRepository.findById(data.idCliente())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO(servicoRepository.buscaServicoPorId(data.idServico()),
                categoriaRepository.buscaCategoriaPorId(data.idCategoria()),usuario, data.prazo(), StatusEnum.SEM_ATENDIMENTO, Calendar.getInstance() );

        return solicitacaoDTO;
    }
}
