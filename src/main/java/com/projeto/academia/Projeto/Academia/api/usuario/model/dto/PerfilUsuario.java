package com.projeto.academia.Projeto.Academia.api.usuario.model.dto;

public enum PerfilUsuario {

    ADM("ADM"), PERSONAL("PERSONAL"), ALUNO("ALUNO");

    private String perfil;

    PerfilUsuario(String perfil){
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }
}
