package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.UserDTO;

public interface ICreateUserService {

    UserDTO createUser(UserDTO userDTO);
}
