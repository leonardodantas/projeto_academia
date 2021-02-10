package com.projeto.academia.Projeto.Academia.api.perfil.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Perfil {

    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_avaliacao")
    private Date dataAvaliacao;

    private double peso;
    private double altura;
    private double imc;

    public Perfil(){}
}
