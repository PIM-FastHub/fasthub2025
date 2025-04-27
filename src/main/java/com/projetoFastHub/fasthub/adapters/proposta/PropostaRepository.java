package com.projetoFastHub.fasthub.adapters.proposta;

import com.projetoFastHub.fasthub.aplicacao.proposta.PropostaModel;
import com.projetoFastHub.fasthub.aplicacao.user.User;

import java.util.List;

public interface PropostaRepository {
    void salvar(PropostaModel p);
    void editar(PropostaModel p);
    void excluir(PropostaModel p);
    List<PropostaModel> listarTodas();
    PropostaModel buscarPorId(Long id);

    List<PropostaModel> buscaPropostaPorPrestador(User user);
}
