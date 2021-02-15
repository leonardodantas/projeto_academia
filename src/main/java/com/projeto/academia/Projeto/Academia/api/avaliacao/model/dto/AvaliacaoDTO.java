package com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto;

import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO implements DataTransferObject {

    @Setter
    @ApiModelProperty(value = "Id da avaliação", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Id do aluno", name = "idAluno", dataType = "String", example = "ahnb594aa41fd98q5")
    private String idAluno;

    @Setter
    @ApiModelProperty(value = "Data da avaliação", name = "dataAvaliacao", dataType = "Date", example = "12/02/2021")
    private Date dataAvaliacao;

    @Setter
    @ApiModelProperty(value = "Data da atualização avaliação", name = "dataAtualizacaoAvaliacao", dataType = "Date", example = "12/02/2021")
    private Date dataAtualizacaoAvaliacao;

    @ApiModelProperty(value = "Peso da avaliação", name = "peso", dataType = "Double", example = "85.10")
    @NotNull(message = "{not.null}") @Positive(message = "positivo")
    private double peso;

    @ApiModelProperty(value = "Altura da avaliação", name = "altura", dataType = "Double", example = "1.75")
    @NotNull(message = "{not.null}") @Positive(message = "positivo")
    private double altura;

    @Setter
    @ApiModelProperty(value = "IMC da avaliação", name = "imc", dataType = "Double", example = "85.10")
    private double imc;
}
