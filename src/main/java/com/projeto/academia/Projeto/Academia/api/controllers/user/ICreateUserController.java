package com.projeto.academia.Projeto.Academia.api.controllers.user;

import com.projeto.academia.Projeto.Academia.api.models.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ICreateUserController {

    @PostMapping("/create")
    @ApiOperation(value = "Criar novo usuario")
    ResponseEntity<?> createUser(@Valid @RequestBody UserDTO request);
}
