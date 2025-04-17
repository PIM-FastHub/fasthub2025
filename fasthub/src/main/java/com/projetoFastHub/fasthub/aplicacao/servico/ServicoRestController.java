package com.projetoFastHub.fasthub.aplicacao.servico;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.servico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("/administracao/servico/")
public class ServicoRestController {


   @Autowired
    EditarServico editarServico;

    @Autowired
    CriarServico criarServico;

    @Autowired
    ExcluirServico excluirServico;

    @Autowired
    ListarServico listarServico;
    @PostMapping("insere")
    public ResponseEntity<String> insere (@RequestBody ServicoResponseDTO servico) {
        String retornoMetodoRetorno = criarServico.criarServico(servico);
        return ResponseEntity.ok(retornoMetodoRetorno);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<String> altera(@PathVariable Long id, @RequestBody ServicoResponseDTO servico) {
        String retornoMetodoEditar = editarServico.editarServico( id,servico);
        return ResponseEntity.ok(retornoMetodoEditar);
    }

    @GetMapping("lista")
    public ResponseEntity<List<ServicoModel>> lista(Model model) {
        List<ServicoModel> listaServico = listarServico.listaTodos();

        return ResponseEntity.ok(listaServico);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> exclui(Model model, @PathVariable(value = "id") Long id) {
      String retornoMetodoExclusao = excluirServico.excluirServico(id);
        return ResponseEntity.ok(retornoMetodoExclusao);
    }

}