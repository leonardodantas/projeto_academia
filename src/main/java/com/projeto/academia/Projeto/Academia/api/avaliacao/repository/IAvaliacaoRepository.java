package com.projeto.academia.Projeto.Academia.api.avaliacao.repository;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAvaliacaoRepository extends JpaRepository<Avaliacao, String> {
    Page<Avaliacao> findAllByIdAluno(String idAluno, Pageable pageable);
    List<Avaliacao> findAllByIdAluno(String idUsuario);
    void deleteAllByIdAluno(String idAluno);
    Optional<Avaliacao> findFirstByIdAlunoOrderByDataAvaliacaoDesc(String idAluno);
    Optional<Avaliacao> findFirstByIdAlunoOrderByDataAtualizacaoAvaliacaoDesc(String idAluno);
}
