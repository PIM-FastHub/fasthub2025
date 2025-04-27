package com.projetoFastHub.fasthub.aplicacao.user;

public enum UserRole {
    ADMIN("admin"),
    CLIENTE("cliente"),
    PRESTADOR("prestador");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
