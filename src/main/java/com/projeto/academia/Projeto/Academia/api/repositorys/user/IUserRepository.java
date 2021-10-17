package com.projeto.academia.Projeto.Academia.api.repositorys.user;

import com.projeto.academia.Projeto.Academia.api.models.entitys.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> findByEmail(String email);
    User save(User user);
    Optional<User> findById(String idUser);
}
