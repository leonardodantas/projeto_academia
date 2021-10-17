package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.dto.LoginDTO;
import com.projeto.academia.Projeto.Academia.api.services.IAutenticationService;
import com.projeto.academia.Projeto.Academia.config.security.service.TokenService;
import com.projeto.academia.Projeto.Academia.utils.token.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AutenticationService implements IAutenticationService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public TokenDTO authentication(LoginDTO request) {
        String token = getToken(request);
        return TokenDTO.bearerOf(token);
    }

    private String getToken(LoginDTO request) {
        try {
            Authentication authentication = authManager.authenticate(request.converter());
            return tokenService.generateToken(authentication);
        } catch (AuthenticationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
