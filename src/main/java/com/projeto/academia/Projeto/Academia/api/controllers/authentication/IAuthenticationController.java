package com.projeto.academia.Projeto.Academia.api.controllers.authentication;

import com.projeto.academia.Projeto.Academia.api.models.dto.LoginDTO;
import com.projeto.academia.Projeto.Academia.utils.token.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAuthenticationController {

    @PostMapping
    ResponseEntity<TokenDTO> authentication(@RequestBody @Valid LoginDTO request);
}
