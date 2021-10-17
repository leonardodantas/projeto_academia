package com.projeto.academia.Projeto.Academia.api.controllers.registration;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IFindRegistrationController {

    @GetMapping
    @ApiOperation(value = "Recuperar todos os cadastros")
    ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable);

    @GetMapping("/{id}")
    @ApiOperation(value = "Recuperar cadastro pelo ID")
    ResponseEntity<?> findAllById(@PathVariable("id") String id);

}
