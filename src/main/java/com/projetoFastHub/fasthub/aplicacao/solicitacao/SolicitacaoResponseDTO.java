package com.projetoFastHub.fasthub.aplicacao.solicitacao;

import jakarta.validation.constraints.NotNull;

public record SolicitacaoResponseDTO(@NotNull  Long idCliente, @NotNull  Long idServico, @NotNull Long idCategoria,@NotNull String prazo, String titulo, String descricao) {
}
