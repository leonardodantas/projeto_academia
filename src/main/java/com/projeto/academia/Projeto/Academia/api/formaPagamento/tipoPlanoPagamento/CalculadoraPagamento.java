package com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.valoresPagamento.service.ValoresPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraPagamento {

    @Autowired
    private ValoresPagamentoService valoresPagamentoService;

    public double calcularValorPagamento(TipoPlanoPagamento tipoPlanoPagamento){
        return valoresPagamentoService.recuperarValorPagamento(tipoPlanoPagamento);
    }

    public double calcularValorComDesconto(CadastroDTO cadastroDTO) {
        double valorComDesconto = 0.0;
        return this.validaValorCorretoParDesconto(cadastroDTO);
    }

    private double validaValorCorretoParDesconto(CadastroDTO cadastroDTO){
        double valorPorcentagem = cadastroDTO.getPorcentagemDesconto();
        return calculaValorDoDesconto(cadastroDTO, valorPorcentagem);
    }

    private double calculaValorDoDesconto(CadastroDTO cadastroDTO, double valorPorcentagem) {
        double valorComDesconto = cadastroDTO.getValorSemDesconto();
        if (valorPorcentagem > 0.0 && valorPorcentagem < 100) {
            valorComDesconto = cadastroDTO.getValorSemDesconto() - cadastroDTO.getValorSemDesconto() * (cadastroDTO.getPorcentagemDesconto() / 100);
        }
        return valorComDesconto;
    }
}
