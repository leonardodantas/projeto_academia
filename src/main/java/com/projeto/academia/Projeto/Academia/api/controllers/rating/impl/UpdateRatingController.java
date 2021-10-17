package com.projeto.academia.Projeto.Academia.api.controllers.rating.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.rating.IUpdateRatingController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.services.IUpdateRatingService;
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
public class UpdateRatingController implements IUpdateRatingController {

    @Autowired
    private IUpdateRatingService updateRatingService;

    @Override
    public ResponseEntity<?> updateRating(RatingDTO request) {
        RatingDTO response = updateRatingService.updateRating(request);
        return ResponseEntity.ok(response);
    }
}
