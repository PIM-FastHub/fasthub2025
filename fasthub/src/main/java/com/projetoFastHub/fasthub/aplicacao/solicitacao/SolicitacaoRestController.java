package com.projetoFastHub.fasthub.aplicacao.solicitacao;


import com.projetoFastHub.fasthub.adapters.categoria.CategoriaRepository;
import com.projetoFastHub.fasthub.adapters.servico.ServicoRepository;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoHistoricoService;
import com.projetoFastHub.fasthub.adapters.solicitacao.SolicitacaoRepository;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoResponseDTO;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoDAO;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao.CriarSolicitacao;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao.EditarSolicitacao;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao.ExcluirSolicitacao;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.solicitacao.ListarTodasSolicitacoes;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SolicitacaoRestController {

    @Autowired
    EditarSolicitacao editarSolicitacao;

    @Autowired
    ListarTodasSolicitacoes listarTodasSolicitacoes;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SolicitacaoHistoricoService solicitacaoHistoricoService;

    @Autowired
    private CriarSolicitacao criarSolicitacao;

    @Autowired
    private ExcluirSolicitacao excluirSolicitacao;

    @Autowired
    private SolicitacaoFactory factory;
    @PostMapping("/administracao/solicitacao/insere")
    public ResponseEntity<SolicitacaoModel> insere(@RequestBody SolicitacaoResponseDTO data){
        SolicitacaoDTO retornoDTO = criarSolicitacao.criarSolicitacao(data);
        SolicitacaoModel solicitacao = factory.deDTO_Para_Model(retornoDTO);
        solicitacaoRepository.insere(solicitacao);
        return ResponseEntity.ok(solicitacao);
    }

    @GetMapping("/administracao/solicitacao/lista")
    public ResponseEntity<List<SolicitacaoModel>> lista(){
       List<SolicitacaoModel> lista = listarTodasSolicitacoes.listaTodasSolicitacoes();

        return ResponseEntity.ok(lista);
    }


    @GetMapping({"/prestador/solicitacao/lista/{idUsuario}","/administracao/solicitacao/lista/{idUsuario}"})
    public ResponseEntity<List<SolicitacaoModel>> listaSolicitacaoPorServico(@PathVariable Long idUsuario) {

        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

       List<SolicitacaoModel> solicitacoes = solicitacaoRepository.listaSolicitacaoPorCategoria(usuario.getCategoria());

        return ResponseEntity.ok(solicitacoes);
    }


    @GetMapping({"/cliente/solicitacao/excluir/{idSolicitacao}", "/administracao/solicitacao/excluir/{idSolicitacao}"})
    public ResponseEntity<String> deletarSolicitacao(@PathVariable Long idSolicitacao){
       String respostaMetodoExclusao = excluirSolicitacao.excluirSolicitacao(idSolicitacao);
       return ResponseEntity.ok(respostaMetodoExclusao);
    }




    @PostMapping({"/cliente/solicitacao/editar/{idSolicitacao}", "/administracao/solicitacao/editar/{idSolicitacao}"})
    public ResponseEntity<String> editarSolicitacao(@PathVariable Long idSolicitacao, @RequestBody @Valid SolicitacaoResponseDTO novosDados){

        String respostaMetodoEditar = editarSolicitacao.editarSolicitacao(idSolicitacao, novosDados);
        return ResponseEntity.ok(respostaMetodoEditar);
    }



    @PostMapping("/cliente/solicitacao/criar")
        public ResponseEntity<String> clienteCriaSolicitacao(@RequestBody SolicitacaoResponseDTO data){
        SolicitacaoDTO retornoDTO = criarSolicitacao.criarSolicitacao(data);
        SolicitacaoModel solicitacao = factory.deDTO_Para_Model(retornoDTO);
        solicitacaoRepository.insere(solicitacao);

        return ResponseEntity.ok("Criado");
    }

    @GetMapping("/administracao/solicitacao/historico")
    public ResponseEntity<List<SolicitacaoDTOHistorico>> historico() {
        List<SolicitacaoModel> lista = listarTodasSolicitacoes.listaTodasSolicitacoes();
        List<SolicitacaoDTOHistorico> historico = solicitacaoHistoricoService.listaHistorico(lista);
        return ResponseEntity.ok(historico);
    }


}
