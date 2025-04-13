package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.usuario;

import com.projetoFastHub.fasthub.aplicacao.user.UserRepository;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.beans.factory.annotation.Autowired;

public interface BanirUsuarioCase {


    String banirUsuario(Long id);
}
