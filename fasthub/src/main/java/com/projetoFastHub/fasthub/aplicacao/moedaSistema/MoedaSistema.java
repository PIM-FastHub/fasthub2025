package com.projetoFastHub.fasthub.aplicacao.moedaSistema;

import jakarta.persistence.Embeddable;

import java.util.Calendar;

import jakarta.persistence.Embeddable;

@Embeddable
public class MoedaSistema {

    private boolean possuiMoeda = true; // todo mundo começa com 1 moeda ativa

    public boolean isPossuiMoeda() {
        return possuiMoeda;
    }

    public void setPossuiMoeda(boolean possuiMoeda) {
        this.possuiMoeda = possuiMoeda;
    }

    public void consumirMoeda() {
        this.possuiMoeda = false;
    }

    public void restaurarMoeda() {
        this.possuiMoeda = true;
    }
}
