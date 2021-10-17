package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.User;
import com.projeto.academia.Projeto.Academia.api.models.dto.UserDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.user.IUserRepository;
import com.projeto.academia.Projeto.Academia.api.services.ICreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements ICreateUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO){
        User user = User.from(userDTO);
        User userSave = userRepository.save(user);
        return UserDTO.from(userSave);
    }

}
