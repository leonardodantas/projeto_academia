package com.projeto.academia.Projeto.Academia.api.models.dto;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.utils.imc.CalculatorIMC;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class RatingDTO implements DataTransferObject {

    @ApiModelProperty(value = "Id da avaliação", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Id do aluno", name = "idAluno", dataType = "String", example = "ahnb594aa41fd98q5")
    private String idStudent;

    @ApiModelProperty(value = "Data da avaliação", name = "dataAvaliacao", dataType = "Date", example = "12/02/2021", hidden = true)
    private LocalDateTime dateRating;

    @ApiModelProperty(value = "Data da atualização avaliação", name = "dataAtualizacaoAvaliacao", dataType = "Date", example = "12/02/2021", hidden = true)
    private LocalDateTime dateRatingUpdate;

    @ApiModelProperty(value = "Peso da avaliação", name = "peso", dataType = "Double", example = "85.10")
    @NotNull(message = "{not.null}") @Positive(message = "positivo")
    private double weight;

    @ApiModelProperty(value = "Altura da avaliação", name = "altura", dataType = "Double", example = "1.75")
    @NotNull(message = "{not.null}") @Positive(message = "positivo")
    private double height;

    @ApiModelProperty(value = "IMC da avaliação", name = "imc", dataType = "Double", example = "85.10", hidden = true)
    private double imc;

    private RatingDTO(Rating rating) {
        this.id = rating.getId();
        this.height = rating.getHeight();
        this.dateRatingUpdate = rating.getDateRatingUpdate();
        this.dateRating = rating.getDateRating();
        this.idStudent = rating.getIdStudent();
        this.imc = rating.getImc();
        this.weight = rating.getWeight();
    }

    private RatingDTO(RatingDTO ratingDTO, Rating rating) {
        this.id = ratingDTO.getId();
        this.height = ratingDTO.getHeight();
        this.dateRatingUpdate = rating.getDateRatingUpdate();
        this.dateRating = ratingDTO.getDateRating();
        this.idStudent = rating.getIdStudent();
        this.imc = CalculatorIMC.calculator(ratingDTO);
        this.weight = ratingDTO.getWeight();
    }

    public static RatingDTO from(Rating rating) {
        return new RatingDTO(rating);
    }

    public RatingDTO with(Rating rating) {
        return new RatingDTO(this, rating);
    }
}
