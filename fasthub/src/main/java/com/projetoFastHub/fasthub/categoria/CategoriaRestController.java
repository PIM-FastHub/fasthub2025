package com.projetoFastHub.fasthub.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController

public class CategoriaRestController {


    @Autowired
    CategoriaDAO categoriaDAO;
    @GetMapping("/administracao/categoria/listar")
    public ResponseEntity<List<CategoriaModel>> listaTodos(){
        List<CategoriaModel> listaCategoria = categoriaDAO.listaTodasCategorias();
        return ResponseEntity.ok(listaCategoria);
    }
    @GetMapping("api/categoria/listar")
    public ResponseEntity<List<CategoriaModel>> listaTodosApi(){
        List<CategoriaModel> listaCategoria = categoriaDAO.listaTodasCategorias();
        return ResponseEntity.ok(listaCategoria);
    }

    @PostMapping("/administracao/categoria/incluir")
    public ResponseEntity<InclusaoCategoriaDTO> incluirCategoria(@RequestBody InclusaoCategoriaDTO dado){
        CategoriaModel categoria = new CategoriaModel();
        categoria.setDescricao(dado.descricao());
        categoria.setDataInclusao(Calendar.getInstance());
        categoriaDAO.insereCategoria(categoria);

        return ResponseEntity.ok(dado);
    }

}
