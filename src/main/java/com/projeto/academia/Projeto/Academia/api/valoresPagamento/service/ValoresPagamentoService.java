package com.projeto.academia.Projeto.Academia.api.valoresPagamento.service;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import com.projeto.academia.Projeto.Academia.api.valoresPagamento.model.ValoresPagamento;
import com.projeto.academia.Projeto.Academia.api.valoresPagamento.repository.IValoresPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValoresPagamentoService {

    @Autowired
    private IValoresPagamentoRepository iValoresPagamentoRepository;

    public double recuperarValorPagamento(TipoPlanoPagamento tipoPagamento){
        Optional<ValoresPagamento> valoresPagamento = iValoresPagamentoRepository.findByTipoPlanoPagamento(tipoPagamento);
        return valoresPagamento.get().getValor();
    }
}
