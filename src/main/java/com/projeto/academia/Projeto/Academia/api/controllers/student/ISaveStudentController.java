package com.projeto.academia.Projeto.Academia.api.controllers.student;

import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ISaveStudentController {

    @PostMapping
    @ApiOperation(value = "Salvar aluno")
    ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO request);
}
