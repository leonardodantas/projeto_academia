package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.LoginDTO;
import com.projeto.academia.Projeto.Academia.utils.token.TokenDTO;

public interface IAutenticationService {

    TokenDTO authentication(LoginDTO request);
}
