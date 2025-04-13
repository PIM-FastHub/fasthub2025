package com.projetoFastHub.fasthub.aplicacao.user;

import com.projetoFastHub.fasthub.casoDeUsos.implementacao.usuario.BanirUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioRestController {

    @Autowired
    BanirUsuario banirUsuario;

    @DeleteMapping("/administracao/usuario/banir/{idUsuario}")
    public ResponseEntity<String> banirUsuario(@PathVariable Long idUsuario){
        String retornoMetodo = banirUsuario.banirUsuario(idUsuario);
        return ResponseEntity.ok(retornoMetodo);
    }
}
