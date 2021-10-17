package com.projeto.academia.Projeto.Academia.api.controllers.student;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IFindStudentController {

    @GetMapping
    @ApiOperation(value = "Recuperar todos os alunos com paginação")
    ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable);

    @GetMapping("/{id}")
    @ApiOperation(value = "Recuperar Aluno por ID")
    ResponseEntity<?> findById(@PathVariable String id);

}