package com.projeto.academia.Projeto.Academia.api.aluno.model;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

@javax.persistence.Entity
public class Aluno extends Entity {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 120)
    private String nome;

    @Column(length = 12)
    private String cpf;

    @Column(length = 120)
    private String email;

    @Column(length = 12, name = "numero_celular")
    private String numeroCelular;

    @Transient
    private List<Avaliacao> avaliacoes;


}
