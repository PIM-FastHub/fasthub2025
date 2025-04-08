package com.projetoFastHub.fasthub.autenticacao;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record LoginResponseDTO(String token, Collection<? extends GrantedAuthority> rules) {
}
