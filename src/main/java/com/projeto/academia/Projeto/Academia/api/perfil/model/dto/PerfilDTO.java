package com.projeto.academia.Projeto.Academia.api.perfil.model.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

public class PerfilDTO {

    private String id;
    private Date dataAvaliacao;
    private double peso;
    private double altura;
    private double imc;
}
