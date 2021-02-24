package com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.valoresPagamento.service.ValoresPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraPagamento {

    @Autowired
    private ValoresPagamentoService valoresPagamentoService;

    public void calcularValorPagamento(CadastroDTO cadastroDTO){
         double valorSemDesconto = valoresPagamentoService.recuperarValorPagamento(cadastroDTO.getTipoPlanoPagamento());
        cadastroDTO.setValorSemDesconto(valorSemDesconto);
    }

    public void calcularPorcentagem(CadastroDTO cadastroDTO) {

        String valorParaPorcentagem = String.valueOf(cadastroDTO.getPorcentagemDesconto());
        double valorPorcentagem = cadastroDTO.getPorcentagemDesconto();

        if (!valorParaPorcentagem.isEmpty()) {

            if (valorPorcentagem > 0.0 && valorPorcentagem < 100) {
                cadastroDTO.setValorComDesconto(cadastroDTO.getValorSemDesconto() - cadastroDTO.getValorSemDesconto() * (cadastroDTO.getPorcentagemDesconto() / 100));
            } else {
                cadastroDTO.setValorComDesconto(cadastroDTO.getValorSemDesconto());
            }

        }
    }
}
