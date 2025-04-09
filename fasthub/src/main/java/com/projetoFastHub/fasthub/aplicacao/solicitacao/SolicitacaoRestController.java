package com.projetoFastHub.fasthub.aplicacao.solicitacao;


import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.CriarSolicitacao;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.ListarTodasSolicitacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
public class SolicitacaoRestController {

    @Autowired
    ListarTodasSolicitacoes listarTodasSolicitacoes;

    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private ServicoDAO servicoDAO;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SolicitacaoDAO solicitacaoDAO;

    @Autowired
    private CriarSolicitacao criarSolicitacao;

    @Autowired
    private SolicitacaoFactory factory;
    @PostMapping("/administracao/solicitacao/insere")
    public ResponseEntity<SolicitacaoModel> insere(@RequestBody SolicitacaoResponseDTO data){
        SolicitacaoDTO retornoDTO = criarSolicitacao.criarSolicitacao(data);
        SolicitacaoModel solicitacao = factory.deDTO_Para_Model(retornoDTO);
        solicitacaoDAO.insere(solicitacao);
        return ResponseEntity.ok(solicitacao);
    }

    @GetMapping("/administracao/solicitacao/lista")
    public ResponseEntity<List<SolicitacaoModel>> lista(){
       List<SolicitacaoModel> lista = listarTodasSolicitacoes.listaTodasSolicitacoes();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/prestador/solicitacao/lista/{idUsuario}")
    public ResponseEntity<List<SolicitacaoModel>> listaSolicitacaoPorServico(@PathVariable Long idUsuario) {

        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

       List<SolicitacaoModel> solicitacoes = solicitacaoDAO.listaSolicitacaoPorCategoria(usuario.getCategoria());

        return ResponseEntity.ok(solicitacoes);
    }



    @PostMapping("/cliente/solicitacao/criar")
        public ResponseEntity<String> clienteCriaSolicitacao(@RequestBody SolicitacaoResponseDTO data){
        SolicitacaoDTO retornoDTO = criarSolicitacao.criarSolicitacao(data);
        SolicitacaoModel solicitacao = factory.deDTO_Para_Model(retornoDTO);
        solicitacaoDAO.insere(solicitacao);

        return ResponseEntity.ok("");
    }


}
