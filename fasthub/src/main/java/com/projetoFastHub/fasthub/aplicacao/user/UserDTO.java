package com.projetoFastHub.fasthub.aplicacao.user;

import org.springframework.stereotype.Component;


public record UserDTO(Long id, String nome, String telefone) {
}
