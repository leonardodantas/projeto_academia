package com.projeto.academia.Projeto.Academia.api.aluno.model.dto;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "ID do aluno", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Nome do aluno", name = "nome", dataType = "String", example = "Leonardo Dantas")
    @NotNull(message = "{not.null}") @Length(min = 20, max = 120, message = "{length.invalid}")
    private String nome;

    @ApiModelProperty(value = "CPF do aluno", name = "cpf", dataType = "String", example = "547812366584")
    @NotNull(message = "{not.null}") @Length(min = 11, max = 14, message = "{length.invalid}")
    @Setter
    private String cpf;

    @ApiModelProperty(value = "Email do aluno", name = "email", dataType = "String", example = "leonardo@email.com.br")
    @Email(message = "{email}")
    @NotNull(message = "{not.null}") @Length(min = 20, max = 120, message = "{length.invalid}")
    private String email;

    @ApiModelProperty(value = "Numero do aluno", name = "numeroCelular", dataType = "String", example = "99999999")
    @NotNull @Length(min = 8, max = 12)
    private String numeroCelular;

    @Setter
    @ApiModelProperty(value = "Lista de avaliações realizadas do aluno", name = "avaliacoes", dataType = "List<String>")
    private List<AvaliacaoDTO> avaliacoes;

}
