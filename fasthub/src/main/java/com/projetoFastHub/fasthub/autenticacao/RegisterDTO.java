package com.projetoFastHub.fasthub.autenticacao;

import com.projetoFastHub.fasthub.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role, String email) {
}
