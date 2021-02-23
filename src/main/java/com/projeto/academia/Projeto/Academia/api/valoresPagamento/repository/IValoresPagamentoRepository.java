package com.projeto.academia.Projeto.Academia.api.valoresPagamento.repository;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import com.projeto.academia.Projeto.Academia.api.valoresPagamento.model.ValoresPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IValoresPagamentoRepository extends JpaRepository<ValoresPagamento, String> {
    Optional<ValoresPagamento> findByTipoPlanoPagamento(TipoPlanoPagamento tipoPagamento);
}
