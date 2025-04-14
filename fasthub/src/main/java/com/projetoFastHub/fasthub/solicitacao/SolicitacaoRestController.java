package com.projetoFastHub.fasthub.solicitacao;


import com.projetoFastHub.fasthub.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.servico.ServicoDAO;
import com.projetoFastHub.fasthub.servico.ServicoModel;
import com.projetoFastHub.fasthub.user.User;
import com.projetoFastHub.fasthub.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class SolicitacaoRestController {

    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private ServicoDAO servicoDAO;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SolicitacaoDAO solicitacaoDAO;

    @PostMapping("/administracao/solicitacao/insere")
    public ResponseEntity<SolicitacaoModel> insere(@RequestBody SolicitacaoResponseDTO data){


        User usuario = userRepository.findById(data.idCliente())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));


        SolicitacaoModel solicitacaoModel = new SolicitacaoModel();

        ServicoModel servicoModel = servicoDAO.buscaPorID(data.idServico());
        solicitacaoModel.setServico(servicoModel);



        solicitacaoModel.setCategoria(categoriaDAO.buscaCategoriaPorId(data.idCategoria()));
        solicitacaoModel.setCliente(usuario);
        solicitacaoModel.setPrazo(data.prazo());
        solicitacaoModel.setStatus(StatusEnum.SEM_ATENDIMENTO);
        solicitacaoModel.setDataCriacao(Calendar.getInstance());


        solicitacaoDAO.insere(solicitacaoModel);

        return ResponseEntity.ok(solicitacaoModel);
    }

    @GetMapping("/administracao/solicitacao/lista")
    public ResponseEntity<List<SolicitacaoModel>> lista(){
        List<SolicitacaoModel> lista = solicitacaoDAO.listaTodasSolicitacoes();


        return ResponseEntity.ok(lista);
    }

    @GetMapping("/administracao/solicitacao/porServico/{id}")
    public ResponseEntity<List<SolicitacaoModel>> listaPorServico(@PathVariable Long id) {

        ServicoModel servico = servicoDAO.buscaServicoPorId(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        List<SolicitacaoModel> solicitacoes = solicitacaoDAO.buscaPorServico(servico);

        if (solicitacoes.isEmpty()) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(solicitacoes);
    }
}
