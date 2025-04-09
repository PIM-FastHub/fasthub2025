package com.projetoFastHub.fasthub.aplicacao.autenticacao;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role, String email, Long categoriaId) {
}
