package com.projeto.academia.Projeto.Academia.api.controllers.rating;

import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ICreateRatingController {

    @PostMapping
    @ApiOperation(value = "Criação de uma nova avaliação")
    ResponseEntity<?> createRating(@Valid @RequestBody RatingDTO request);
}
