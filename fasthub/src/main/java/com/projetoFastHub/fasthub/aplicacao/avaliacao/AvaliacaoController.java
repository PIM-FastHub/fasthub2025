package com.projetoFastHub.fasthub.aplicacao.avaliacao;

import com.projetoFastHub.fasthub.adapters.avaliacao.AvaliacaoRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.avaliacao.AvaliacaoCriarCase;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.avaliacao.AvaliacaoEditarCase;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.servico.EditarServicoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao/")
@CrossOrigin(origins = "*")
public class AvaliacaoController {
    @Autowired
    AvaliacaoCriarCase avaliacaoCriarCase;

    @Autowired
    AvaliacaoRepository repository;

    @Autowired
    AvaliacaoEditarCase avaliacaoEditarCase;
    @PostMapping("criar")
    public ResponseEntity<String> avalia(@RequestBody AvaliacaoDTO data){
        String retornoMetodoCriar = avaliacaoCriarCase.avaliar(data);
        return ResponseEntity.ok(retornoMetodoCriar);
    }


    @PutMapping("alterar/{id}")
    public ResponseEntity<String> edita(@RequestBody AvaliacaoDTO data, @PathVariable Long id){
        String retornoMetodoAltera = avaliacaoEditarCase.editar(data,id);
        return ResponseEntity.ok(retornoMetodoAltera);
    }

    @GetMapping("listar")
    public ResponseEntity<List<AvaliacaoModel>> listaTudo(){
        return ResponseEntity.ok(repository.listaTudo());
    }

}
