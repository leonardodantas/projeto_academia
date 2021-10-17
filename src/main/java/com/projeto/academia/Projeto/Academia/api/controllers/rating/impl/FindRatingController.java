package com.projeto.academia.Projeto.Academia.api.controllers.rating.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.rating.IFindRatingController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.services.IFindRatingService;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentRatingService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api(tags = "Avaliação")
@RequestMapping("/avaliacoes")
public class FindRatingController implements IFindRatingController {

    @Autowired
    private IFindRatingService findRatingService;

    @Autowired
    private IFindStudentRatingService findStudentRatingService;

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        CollectionResponse<RatingDTO, Rating> response = findRatingService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> findAllStudent(Pageable pageable, String id) {
        CollectionResponse<RatingDTO, Rating> response = findStudentRatingService.findAllByIdStudent(id, pageable);
        return ResponseEntity.ok(response);
    }
}
