package com.projetoFastHub.fasthub.aplicacao.moedaSistema;

import jakarta.persistence.Embeddable;

import java.util.Calendar;

@Embeddable
public class MoedaSistema {
    private boolean isAtivo;
    private double valor;
    private Calendar dataAdquirida;

}
