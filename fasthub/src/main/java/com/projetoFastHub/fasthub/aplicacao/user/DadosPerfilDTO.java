package com.projetoFastHub.fasthub.aplicacao.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;


public record DadosPerfilDTO(@NotNull String nome,@NotNull String sobrenome, String cep,
                             String rua, String estado, Long categoriaId, String email,
                             String telefone) {


}
