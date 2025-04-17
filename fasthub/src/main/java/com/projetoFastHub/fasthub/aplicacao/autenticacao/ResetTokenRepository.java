package com.projetoFastHub.fasthub.aplicacao.autenticacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.LongFunction;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    public ResetToken findByToken(String token);
}
