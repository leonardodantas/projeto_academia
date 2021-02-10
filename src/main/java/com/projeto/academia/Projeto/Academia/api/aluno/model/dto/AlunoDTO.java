package com.projeto.academia.Projeto.Academia.api.aluno.model.dto;

import com.projeto.academia.Projeto.Academia.api.perfil.model.dto.PerfilDTO;
import com.projeto.academia.Projeto.Academia.generico.model.dto.DataTransferObject;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Builder
@Getter

public class AlunoDTO implements DataTransferObject {

    private String id;

    @NotNull @Length(min = 20, max = 120)
    private String nome;

    @NotNull @Length(min = 11, max = 12)
    private String cpf;

    @NotNull @Length(min = 20, max = 120)
    private String email;

    @NotNull @Length(min = 8, max = 12)
    private String numeroCelular;

    private PerfilDTO perfil;
}
