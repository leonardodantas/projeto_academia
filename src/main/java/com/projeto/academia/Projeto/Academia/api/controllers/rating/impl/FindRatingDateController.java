package com.projeto.academia.Projeto.Academia.api.controllers.rating.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.rating.IFindRatingDateController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentRatingService;
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
public class FindRatingDateController implements IFindRatingDateController {

    @Autowired
    private IFindStudentRatingService findStudentRatingService;

    @Override
    public ResponseEntity<?> findLastRatingStudent(String id) {
        RatingDTO response = findStudentRatingService.findLastRatingStudent(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> findLastRatingStudentUpdate(String id) {
        RatingDTO response = findStudentRatingService.findLastRatingStudentUpdate(id);
        return ResponseEntity.ok(response);
    }
}
