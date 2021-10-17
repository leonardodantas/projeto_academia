package com.projeto.academia.Projeto.Academia.api.controllers.rating;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IFindRatingDateController {

    @GetMapping("/ultimaAvaliacao/{idAluno}")
    @ApiOperation(value = "Recupera ultima avaliação do aluno de acordo com o atributo Data de Avaliação")
    ResponseEntity<?> findLastRatingStudent(@PathVariable String id);

    @GetMapping("/ultimaAvaliacao/atualizada/{idAluno}")
    @ApiOperation(value = "Recupera ultima avaliação do aluno de acordo com o atributo Data de atualização da Avaliação")
    ResponseEntity<?> findLastRatingStudentUpdate(@PathVariable String id);

}
