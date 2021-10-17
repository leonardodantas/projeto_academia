package com.projeto.academia.Projeto.Academia.api.repositorys.user.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.User;
import com.projeto.academia.Projeto.Academia.api.repositorys.user.IUserRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(String idUser) {
        return userRepository.findById(idUser);
    }
}
