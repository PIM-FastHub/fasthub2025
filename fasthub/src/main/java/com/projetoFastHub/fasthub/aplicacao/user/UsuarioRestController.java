package com.projetoFastHub.fasthub.aplicacao.user;

import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.usuario.DadosMeuPerfil;
import com.projetoFastHub.fasthub.casoDeUsos.implementacao.usuario.BanirUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioRestController {


    @Autowired
    UserRepository repository;

    @Autowired
    BanirUsuario banirUsuario;

    @Autowired
    DadosMeuPerfil dadosMeuPerfil;

    @DeleteMapping("/administracao/usuario/banir/{idUsuario}")
    public ResponseEntity<String> banirUsuario(@PathVariable Long idUsuario){
        String retornoMetodo = banirUsuario.banirUsuario(idUsuario);
        return ResponseEntity.ok(retornoMetodo);
    }

    @GetMapping({"/cliente/meuperfil/{id}", "/prestador/meuperfil/{id}","/administracao/meuperfil/{id}"})
    public ResponseEntity<DadosPerfilDTO> retonaDadosPerfil(@PathVariable Long id){
        //precisa validar a identidade aqui//

        User usuario = repository.findById(id).orElse(null);
        DadosPerfilDTO dados = dadosMeuPerfil.retornaDadosMeuPerfil(usuario);
        return ResponseEntity.ok(dados);
    }

    @PutMapping({"/cliente/meuperfil/alterar/{id}","/prestador/meuperfil/alterar/{id}",
            "/prestador/meuperfil/alterar/{id}" })
    public ResponseEntity<String>alterar(@RequestBody DadosPerfilDTO dados, @PathVariable Long id){
        User usuario = dadosMeuPerfil.alterar(dados, id);

        String retornoAtravesDoMetodo= usuario !=null ?"Editado Com Sucesso": "Erro ao editar Perfil";
        System.out.println(usuario.getId());
        return ResponseEntity.ok(retornoAtravesDoMetodo);
    }



    @GetMapping("/administracao/listaUsuario/permissao")
    public List<UsuarioListagemDTO> listarUsuarios(List<User> usuarios) {
        List<UsuarioListagemDTO> lista = new ArrayList<>();

        for (User user : usuarios) {
            String nomeCompleto = user.getNome() + " " + user.getSobrenome();
            StatusUsuario status = user.getStatusEnum(); // agora pegando do enum
            String permissao = user.getRole().name(); // ADMIN, CLIENTE ou PRESTADOR

            UsuarioListagemDTO dto = new UsuarioListagemDTO(nomeCompleto, status, permissao);
            lista.add(dto);
        }

        return lista;
    }




}
