package com.projeto.academia.Projeto.Academia.api.controllers.registration;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ICreateRegistrationController {

    @PostMapping
    @ApiOperation(value = "Criação de um novo cadastro")
    ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDTO request);

}
