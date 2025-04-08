package com.projetoFastHub.fasthub.Endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Embeddable
public class Endereco {
    private String cep;
    private String rua;
    private String logradouro;
    private String estado;

}
