package com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.api.valoresPagamento.service.ValoresPagamentoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculadoraPagamentoTest {

    @InjectMocks
    private CalculadoraPagamento calculadoraPagamento;

    @Mock
    private ValoresPagamentoService valoresPagamentoService;

    @Test
    public void deveRetornarValorPagamento(){
        when(valoresPagamentoService.recuperarValorPagamento(TipoPlanoPagamento.MENSAL)).thenReturn(75.0);
        double valorPagamento = calculadoraPagamento.calcularValorPagamento(TipoPlanoPagamento.MENSAL);
        assertThat(valorPagamento).isEqualTo(75.0);
    }

    @Test
    public void deveCalcularValorComDesconto10Porcento(){
        FormaPagamentoDTO formaPagamentoDTO = FormaPagamentoDTO.builder()
                .valorSemDesconto(100)
                .porcentagemDesconto(10)
                .build();
        CadastroDTO cadastroDTO = CadastroDTO.builder()
                .formaPagamento(formaPagamentoDTO)
                .build();

        double valorComDesconto = calculadoraPagamento.calcularValorComDesconto(cadastroDTO);
        assertThat(valorComDesconto).isEqualTo(90);
    }


    @Test
    public void deveCalcularValorComDesconto0Porcento(){
        FormaPagamentoDTO formaPagamentoDTO = FormaPagamentoDTO.builder()
                .valorSemDesconto(100)
                .porcentagemDesconto(0)
                .build();
        CadastroDTO cadastroDTO = CadastroDTO.builder()
                .formaPagamento(formaPagamentoDTO)
                .build();

        double valorComDesconto = calculadoraPagamento.calcularValorComDesconto(cadastroDTO);
        assertThat(valorComDesconto).isEqualTo(100);
    }


    @Test
    public void deveCalcularValorComDesconto33Porcento(){
        FormaPagamentoDTO formaPagamentoDTO = FormaPagamentoDTO.builder()
                .valorSemDesconto(100)
                .porcentagemDesconto(33)
                .build();
        CadastroDTO cadastroDTO = CadastroDTO.builder()
                .formaPagamento(formaPagamentoDTO)
                .build();

        double valorComDesconto = calculadoraPagamento.calcularValorComDesconto(cadastroDTO);
        assertThat(valorComDesconto).isEqualTo(67);
    }
}
