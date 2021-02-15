package com.projeto.academia.Projeto.Academia.api.avaliacao.repository;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAvaliacaoRepository extends JpaRepository<Avaliacao, String> {
    Page<Avaliacao> findAllByIdAluno(String idAluno, Pageable pageable);
    void deleteByIdAluno(String idAluno);
    List<Avaliacao> findAllByIdAluno(String idUsuario);
}
