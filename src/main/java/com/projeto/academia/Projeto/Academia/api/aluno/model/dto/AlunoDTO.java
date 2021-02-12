package com.projeto.academia.Projeto.Academia.api.aluno.model.dto;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AlunoDTO implements DataTransferObject {

    @Setter
    private String id;

    @NotNull(message = "{not.null}") @Length(min = 20, max = 120, message = "{length.invalid}")
    private String nome;

    @NotNull(message = "{not.null}") @Length(min = 11, max = 12, message = "{length.invalid}")
    private String cpf;

    @Email(message = "{email}")
    @NotNull(message = "{not.null}") @Length(min = 20, max = 120, message = "{length.invalid}")
    private String email;

    @NotNull @Length(min = 8, max = 12)
    private String numeroCelular;

    private List<AvaliacaoDTO> avaliacoes;

}
