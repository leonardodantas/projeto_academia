package com.projeto.academia.Projeto.Academia.api.aluno.repository;


import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAlunoRepository extends JpaRepository<Aluno, String> {

    Optional<Aluno> findByCpf(String cpf);
}
