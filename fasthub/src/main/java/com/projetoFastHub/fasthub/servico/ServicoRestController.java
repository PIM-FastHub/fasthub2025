package com.projetoFastHub.fasthub.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("/administracao/servico/")
public class ServicoRestController {

    @Autowired
    private ServicoDAO servicoDAO;

    @PostMapping("insere")
    public String insere(Model model, ServicoModel servico) {
        ServicoModel servicoNovo = ServicoModel.builder()
                .nome(servico.getNome())
                .descricao(servico.getDescricao())
                .dataInclusao(Calendar.getInstance())
                .build();

        servicoDAO.insereServico(servicoNovo);
        return "redirect:/administracao/servico/lista";
    }

    @PostMapping("altera")
    public String altera(Model model, ServicoModel servico) {
        ServicoModel servicoNovo = ServicoModel.builder()
                .nome(servico.getNome())
                .descricao(servico.getDescricao())
                .dataInclusao(servico.getDataInclusao())
                .dataAlteracao(Calendar.getInstance())
                .build();
        servicoDAO.alteraServico(servicoNovo);
        return "redirect:/administracao/servico/lista";
    }

    @GetMapping("lista")
    public String lista(Model model) {
        List<ServicoModel> listaServico = servicoDAO.listaTodosServicos();
        model.addAttribute("listaServico", listaServico);
        return "administracao/servico/lista";
    }

    @GetMapping("/administracao/servico/exclui/{id}")
    public String exclui(Model model, @PathVariable(value = "id") Long id) {
        ServicoModel servico = servicoDAO.buscaServicoPorId(id);
        servicoDAO.excluirServico(servico);
        return "redirect:/administracao/servico/lista";
    }

}