package com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento;

import com.projeto.academia.Projeto.Academia.api.valoresPagamento.service.ValoresPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CalculadoraPagamento {

    @Autowired
    private ValoresPagamentoService valoresPagamentoService;

    public double calcularValorPagamento(TipoPlanoPagamento tipoPagamento){
        return valoresPagamentoService.recuperarValorPagamento(tipoPagamento);
    }
}
