package com.projeto.academia.Projeto.Academia.api.models.dto;

public enum ProfileUser {

    ADM("ADM"), PERSONAL("PERSONAL"), STUDENT("ALUNO");

    private String profile;

    ProfileUser(String profile){
        this.profile = profile;
    }
}
