package com.projeto.academia.Projeto.Academia.api.avaliacao.model;

import com.projeto.academia.Projeto.Academia.generico.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

@javax.persistence.Entity
public class Avaliacao implements Entity {

    @Id
    @Column(length = 50)
    private String id;

    @Column(name = "id_aluno", length = 50)
    private String idAluno;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_avaliacao")
    private Date dataAvaliacao;

    private double peso;
    private double altura;
    private double imc;

}
