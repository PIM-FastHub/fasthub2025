package com.projetoFastHub.fasthub.aplicacao.user;

public class UsuarioListagemDTO {
    private String nome;
    private StatusUsuario status;
    private String permissao;

    public UsuarioListagemDTO(String nome, StatusUsuario status, String permissao) {
        this.nome = nome;
        this.status = status;
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}
