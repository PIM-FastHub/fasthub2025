package com.projetoFastHub.fasthub.aplicacao.servico;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaDAO;
import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
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
    private ServicoDAO servicoDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @PostMapping("insere")
    public ResponseEntity<ServicoModel> insere (@RequestBody ServicoResponseDTO servico) {
        System.out.println(servico.categoriaId());
        System.out.println(servico.descricao());
        System.out.println(servico.nome());

        CategoriaModel categoriaModel = categoriaDAO.buscaCategoriaPorId(servico.categoriaId());
        System.out.println(categoriaModel.getId() + " "+ categoriaModel.getDescricao()+ " "+ categoriaModel.getDataInclusao());

        ServicoModel servicoNovo = new ServicoModel();
        servicoNovo.setCategoria(categoriaModel);
        servicoNovo.setNome(servico.nome());
        servicoNovo.setDescricao(servico.descricao());
        servicoNovo.setDataInclusao(Calendar.getInstance());

        servicoDAO.insereServico(servicoNovo);
        return ResponseEntity.ok(servicoNovo);
    }

    @PostMapping("altera")
    public String altera(Model model, ServicoModel servico) {
//        ServicoModel servicoNovo = ServicoModel.builder()
//                .nome(servico.getNome())
//                .descricao(servico.getDescricao())
//                .dataInclusao(servico.getDataInclusao())
//                .dataAlteracao(Calendar.getInstance())
//                .build();
        //servicoDAO.alteraServico(null);
        return "redirect:/administracao/servico/lista";
    }

    @GetMapping("lista")
    public ResponseEntity<List<ServicoModel>> lista(Model model) {
        List<ServicoModel> listaServico = servicoDAO.listaTodosServicos();

        return ResponseEntity.ok(listaServico);
    }

    @GetMapping("/administracao/servico/exclui/{id}")
    public String exclui(Model model, @PathVariable(value = "id") Long id) {
        ServicoModel servico = servicoDAO.buscaServicoPorId(id);
        servicoDAO.excluirServico(servico);
        return "redirect:/administracao/servico/lista";
    }

}