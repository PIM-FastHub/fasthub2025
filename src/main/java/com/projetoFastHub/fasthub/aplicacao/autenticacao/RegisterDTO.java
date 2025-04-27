package com.projetoFastHub.fasthub.aplicacao.autenticacao;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.user.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String login, @NotNull  String password, UserRole role,
                          String email, Long categoriaId,
                          String nome, String sobrenome, String documento,
                          String cep, String endereco, String estado ) {
}

