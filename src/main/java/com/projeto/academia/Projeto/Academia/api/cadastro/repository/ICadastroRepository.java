package com.projeto.academia.Projeto.Academia.api.cadastro.repository;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICadastroRepository extends JpaRepository<Cadastro, String> {
}
