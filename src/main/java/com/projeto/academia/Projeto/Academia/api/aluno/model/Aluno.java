package com.projeto.academia.Projeto.Academia.api.aluno.model;

import com.projeto.academia.Projeto.Academia.api.perfil.model.Perfil;
import com.projeto.academia.Projeto.Academia.generico.model.Entity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@Getter

@javax.persistence.Entity
public class Aluno implements Entity {

    @Id
    private String id;

    @Column(length = 120)
    private String nome;

    @Column(length = 12)
    private String cpf;

    @Column(length = 120)
    private String email;

    @Column(length = 12, name = "numero_celular")
    private String numeroCelular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="perfil_id", referencedColumnName = "id")
    private Perfil perfil;

    public Aluno(){}

}
