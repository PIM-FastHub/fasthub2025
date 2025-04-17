package com.projetoFastHub.fasthub.casoDeUsos.casosDeUso.usuario;

import com.projetoFastHub.fasthub.aplicacao.user.DadosPerfilDTO;
import com.projetoFastHub.fasthub.aplicacao.user.User;

public interface DadosMeuPerfil {
 DadosPerfilDTO retornaDadosMeuPerfil(User user);
 User alterar(DadosPerfilDTO dto, Long id);
}
