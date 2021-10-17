package com.projeto.academia.Projeto.Academia.api.controllers.rating.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.rating.IRemoveRatingController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.services.IRemoveRatingService;
import com.projeto.academia.Projeto.Academia.api.services.IRemoveRatingStudentService;
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
public class RemoveRatingController implements IRemoveRatingController {

    @Autowired
    private IRemoveRatingService removeRatingService;

    @Autowired
    private IRemoveRatingStudentService removeRatingStudentService;

    @Override
    public ResponseEntity<?> removeRating(String id) {
        RatingDTO response = removeRatingService.removeRating(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> removeAllRatingStudent(String id) {
        StudentDTO response = removeRatingStudentService.removeAllRatingOfStudent(id);
        return ResponseEntity.ok(response);
    }
}
