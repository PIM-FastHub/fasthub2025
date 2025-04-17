package com.projetoFastHub.fasthub.aplicacao.moedaSistema;


import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserDAO;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoedaController {
    @Autowired
    UserRepository userRepository;

    @PostMapping({"/prestador/comprar/moeda/{id}"})
    public ResponseEntity<?> compraMoeda(@PathVariable Long id){
        try{
            User usuario = userRepository.findById(id).orElse(null);
            MoedaSistema moedaSistema = usuario.getMoedaSistema();
            moedaSistema.setPossuiMoeda(true);
            usuario.setMoedaSistema(moedaSistema);

            userRepository.save(usuario);
            return ResponseEntity.ok("Usuario adquiriu moeda do sistema");

        }catch (Exception e){
            return ResponseEntity.ok("Erro ao adquirir moeda do sistema "+e.getMessage());
        }

    }

}
