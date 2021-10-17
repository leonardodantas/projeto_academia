package com.projeto.academia.Projeto.Academia.api.repositorys.user;

import com.projeto.academia.Projeto.Academia.api.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String username);
}
