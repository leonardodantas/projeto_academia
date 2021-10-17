package com.projeto.academia.Projeto.Academia.api.controllers.rating;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IFindRatingController {

    @GetMapping
    @ApiOperation(value = "Recupera todas as avaliações")
    ResponseEntity<?> findAll(@PageableDefault(page = 0,size = 20) Pageable pageable);

    @GetMapping("/{idAluno}")
    @ApiOperation(value = "Recupera todas as avaliações de determinado aluno pelo seu ID")
    ResponseEntity<?> findAllStudent(@PageableDefault(page = 0,size = 20) Pageable pageable, @PathVariable String id);

}
