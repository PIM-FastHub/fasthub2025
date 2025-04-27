package com.projetoFastHub.fasthub.aplicacao.servico;

import jakarta.validation.constraints.NotNull;

public record ServicoResponseDTO(@NotNull String nome, @NotNull String descricao, @NotNull Long categoriaId)  {
}
