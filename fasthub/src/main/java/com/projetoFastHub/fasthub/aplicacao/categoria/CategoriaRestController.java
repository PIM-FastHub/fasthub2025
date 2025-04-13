package com.projetoFastHub.fasthub.aplicacao.categoria;

import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.CriarCategoria;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.EditarCategoria;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.ExcluirCategoria;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.categoria.ListarCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController

public class CategoriaRestController {


    @Autowired
    ListarCategoria listarCategoria;

    @Autowired
    CriarCategoria criarCategoria;

    @Autowired
    ExcluirCategoria excluirCategoria;

    @Autowired
    EditarCategoria editarCategoria;

    @GetMapping("/administracao/categoria/listar")
    public ResponseEntity<List<CategoriaModel>> listaTodos(){
        List<CategoriaModel> listaCategoria = listarCategoria.listaTodasCategorias();
        return ResponseEntity.ok(listaCategoria);
    }
    @GetMapping("api/categoria/listar")
    public ResponseEntity<List<CategoriaModel>> listaTodosApi(){
        List<CategoriaModel> listaCategoria = listarCategoria.listaTodasCategorias();
        return ResponseEntity.ok(listaCategoria);
    }

    @PostMapping("/administracao/categoria/incluir")
    public ResponseEntity<String> incluirCategoria(@RequestBody InclusaoCategoriaDTO dado){

        String retornoMetodoInclusao = criarCategoria.criarCategoria(dado);
        return ResponseEntity.ok(retornoMetodoInclusao);
    }

    @DeleteMapping("/administracao/categoria/excluir/{idCategoria}")
    public ResponseEntity<String> excluirCategoria(@PathVariable Long idCategoria){
        String respostaMetodoExclusao= excluirCategoria.excluirCategoria(idCategoria);
        return ResponseEntity.ok(respostaMetodoExclusao);
    }

    @PutMapping("/administracao/categoria/editar/{idCategoria}")
    public ResponseEntity<String> editaCategoria(@PathVariable Long idCategoria, @RequestBody InclusaoCategoriaDTO data){
        String respostaMetodoEditar= editarCategoria.editarCategoria(idCategoria,data);
        return ResponseEntity.ok(respostaMetodoEditar);

    }

}
