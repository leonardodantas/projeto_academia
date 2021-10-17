package com.projeto.academia.Projeto.Academia.api.controllers.rating;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

public interface IRemoveRatingController {

    @DeleteMapping("/{idAvaliacao}")
    @Transactional
    @ApiOperation(value = "Remove uma avaliação a partir do ID da avaliação")
    ResponseEntity<?> removeRating(@PathVariable String id);

    @DeleteMapping("/aluno/{idAluno}")
    @Transactional
    @ApiOperation(value = "Remove todas as avaliações de uma aluno a partir do ID do aluno")
    ResponseEntity<?> removeAllRatingStudent(@PathVariable String id);

}
