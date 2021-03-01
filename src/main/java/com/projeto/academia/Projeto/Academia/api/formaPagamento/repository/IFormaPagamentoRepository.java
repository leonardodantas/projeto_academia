package com.projeto.academia.Projeto.Academia.api.formaPagamento.repository;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFormaPagamentoRepository extends JpaRepository<FormaPagamento, String> {
}
