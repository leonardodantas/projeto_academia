package com.projeto.academia.Projeto.Academia.api.controllers.user.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.user.ICreateUserController;
import com.projeto.academia.Projeto.Academia.api.models.dto.UserDTO;
import com.projeto.academia.Projeto.Academia.api.services.ICreateUserService;
import com.projeto.academia.Projeto.Academia.api.services.impl.CreateUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@Api(tags = "Usuarios")
@RequestMapping("/usuario")
public class CreateUserController implements ICreateUserController {

    @Autowired
    private ICreateUserService createUserService;

    @Override
    public ResponseEntity<?> createUser(UserDTO request) {
        UserDTO response = createUserService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
