package com.projetoFastHub.fasthub.aplicacao.categoria;

import jakarta.validation.constraints.NotNull;

public record InclusaoCategoriaDTO(@NotNull String descricao) {
}
