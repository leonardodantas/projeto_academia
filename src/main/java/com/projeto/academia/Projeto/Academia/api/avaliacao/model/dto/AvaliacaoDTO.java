package com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto;

import com.projeto.academia.Projeto.Academia.generico.model.dto.DataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class AvaliacaoDTO implements DataTransferObject {

    private String id;
    private Date dataAvaliacao;
    private double peso;
    private double altura;
    private double imc;
}
