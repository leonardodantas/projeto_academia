package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.utils.imc.CalculatorIMC;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Table(name = "avaliacao")
@javax.persistence.Entity
public class Rating extends Entity {
    @Id
    @Column(length = 50)
    private String id;

    @Column(name = "id_aluno", length = 50)
    private String idStudent;

    @Column(name = "data_avaliacao")
    private LocalDateTime dateRating;

    @Column(name = "data_atualizacao_avaliacao")
    private LocalDateTime dateRatingUpdate;

    private double weight;
    private double height;
    private double imc;

    private Rating(RatingDTO rating) {
        this.id = rating.getId();
        this.height = rating.getHeight();
        this.dateRatingUpdate = rating.getDateRatingUpdate();
        this.dateRating = rating.getDateRating();
        this.idStudent = rating.getIdStudent();
        this.imc = CalculatorIMC.calculator(rating);
        this.weight = rating.getWeight();
    }

    private Rating(RatingDTO rating, LocalDateTime date) {
        this.id = UUID.randomUUID().toString();
        this.height = rating.getHeight();
        this.dateRating = date;
        this.idStudent = rating.getIdStudent();
        this.imc = CalculatorIMC.calculator(rating);
        this.weight = rating.getWeight();
    }

    public static Rating from(RatingDTO rating) {
        return new Rating(rating);
    }

    public static Rating createOf(RatingDTO rating) {
        return new Rating(rating, LocalDateTime.now());
    }

    public void updateDate() {
        this.dateRatingUpdate = LocalDateTime.now();
    }
}
