package com.projeto.academia.Projeto.Academia.api.controllers.rating.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.rating.ICreateRatingController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.services.ICreateRatingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api(tags = "Avaliação")
@RequestMapping("/avaliacoes")
public class CreateRatingController implements ICreateRatingController {

    @Autowired
    private ICreateRatingService createRatingService;

    @Override
    public ResponseEntity<?> createRating(RatingDTO request) {
        RatingDTO response = createRatingService.createRating(request);
        return ResponseEntity.ok(response);
    }
}
