package com.projeto.academia.Projeto.Academia.api.controllers.rating;

import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IUpdateRatingController {

    @PutMapping
    @ApiOperation(value = "Atualização de uma avaliação")
    ResponseEntity<?> updateRating(@Valid @RequestBody RatingDTO request);

}
