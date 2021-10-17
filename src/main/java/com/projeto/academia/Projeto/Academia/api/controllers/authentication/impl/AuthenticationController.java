package com.projeto.academia.Projeto.Academia.api.controllers.authentication.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.authentication.IAuthenticationController;
import com.projeto.academia.Projeto.Academia.api.models.dto.LoginDTO;
import com.projeto.academia.Projeto.Academia.api.services.IAutenticationService;
import com.projeto.academia.Projeto.Academia.utils.token.TokenDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(tags = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthenticationController implements IAuthenticationController {

    @Autowired
    private IAutenticationService autenticationService;

    @Override
    public ResponseEntity<TokenDTO> authentication(LoginDTO request) {
        TokenDTO response = autenticationService.authentication(request);
        return ResponseEntity.ok(response);
    }
}
