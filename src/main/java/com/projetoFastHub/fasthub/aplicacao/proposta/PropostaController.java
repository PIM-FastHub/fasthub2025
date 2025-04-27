package com.projetoFastHub.fasthub.aplicacao.proposta;


import com.projetoFastHub.fasthub.adapters.proposta.PropostaRepository;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.proposta.PropostaCriarCase;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.proposta.PropostaEditarCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PropostaController {

    @Autowired
    PropostaCriarCase propostaCriarCase;

    @Autowired
    PropostaEditarCase propostaEditarCase;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropostaRepository repository;

    @PostMapping("/prestador/proposta/enviar")
    public ResponseEntity<String> enviarProposta(@RequestBody @Valid  PropostaCriarDTO data) {
        String retornoMetodoCriar = propostaCriarCase.criarProposta(data);
        return ResponseEntity.ok(retornoMetodoCriar);

    }





    @PutMapping("/prestador/proposta/editar/{id}")
    public ResponseEntity<String> editar(@RequestBody @Valid  PropostaEditarDTO data, @PathVariable Long id){
        String retornoMetodoEditar = propostaEditarCase.editar(data, id);
        return ResponseEntity.ok(retornoMetodoEditar);
    }

   @DeleteMapping("/prestador/proposta/excluir/{id}")
   public ResponseEntity<String> excluir(@PathVariable Long id){
        try {
            repository.excluir(repository.buscarPorId(id));
            return ResponseEntity.ok("Excluido Com Sucesso");
        }catch (Exception e){
            return ResponseEntity.ok("Erro ao Excluir "+ e.getMessage());
        }

   }
    @GetMapping("/prestador/proposta/listar/{id}")
    public ResponseEntity<List<PropostaModel>> lsitarPorIdUsuario(@PathVariable Long id){
        try{
            return ResponseEntity.ok(repository.buscaPropostaPorPrestador(userRepository.findById(id).orElse(null)));

        }catch (Exception e){
            System.out.println("Erro de listar proposta por prestador "+ e.getMessage());
            return null;
        }
    }



}
