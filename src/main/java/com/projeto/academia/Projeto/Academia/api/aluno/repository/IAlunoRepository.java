package com.projeto.academia.Projeto.Academia.api.aluno.repository;


import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlunoRepository extends JpaRepository<Aluno, String> {
}
