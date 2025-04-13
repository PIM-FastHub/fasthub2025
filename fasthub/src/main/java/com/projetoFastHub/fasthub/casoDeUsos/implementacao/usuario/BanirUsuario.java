package com.projetoFastHub.fasthub.casoDeUsos.implementacao.usuario;

import com.projetoFastHub.fasthub.aplicacao.user.User;
import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.usuario.BanirUsuarioCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class BanirUsuario implements BanirUsuarioCase {

    @Autowired UserRepository repository;
    @Override
    public String banirUsuario(Long id) {
        try {
            Optional<User> usuario = repository.findById(id);
            if (usuario.isPresent()) {
                repository.delete(usuario.get());
                return "Usuário banido com sucesso";
            } else {
                return "Usuário não encontrado";
            }
        } catch (Exception e) {

            return "Erro ao tentar banir o usuário: " + e.getMessage();
        }
    }
}
