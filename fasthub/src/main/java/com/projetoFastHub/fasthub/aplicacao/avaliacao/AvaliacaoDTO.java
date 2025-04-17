package com.projetoFastHub.fasthub.aplicacao.avaliacao;

import jakarta.validation.constraints.NotNull;

public record AvaliacaoDTO(@NotNull double nota, String comentario, @NotNull Long solicitacaoId) {}

