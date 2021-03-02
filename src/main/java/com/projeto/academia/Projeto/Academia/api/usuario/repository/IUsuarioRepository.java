package com.projeto.academia.Projeto.Academia.api.usuario.repository;

import com.projeto.academia.Projeto.Academia.api.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String username);
}
