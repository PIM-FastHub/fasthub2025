package com.projetoFastHub.fasthub.aplicacao.solicitacao;

import com.projetoFastHub.fasthub.aplicacao.categoria.CategoriaModel;
import com.projetoFastHub.fasthub.aplicacao.servico.ServicoModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;
import jakarta.persistence.*;

import java.util.Calendar;

public record SolicitacaoDTO(
        ServicoModel servicoModel,
        CategoriaModel categoriaModel,
        User cliente,
        String prazo,
        StatusEnum status,
        Calendar dataCriacao
) {}


