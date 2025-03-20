package com.projetoFastHub.fasthub.configuracoesDoSistema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ConfiguracoesDoSistema {
    @Id
    private String propriedade;
    private String valor;

    public ConfiguracoesDoSistema(String propriedade, String valor) {
        this.propriedade = propriedade;
        this.valor = valor;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
