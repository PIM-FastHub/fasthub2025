package com.projetoFastHub.fasthub.aplicacao.Endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Embeddable
public class Endereco {
    private String cep;
    private String rua;
    private String logradouro;
    private String estado;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
