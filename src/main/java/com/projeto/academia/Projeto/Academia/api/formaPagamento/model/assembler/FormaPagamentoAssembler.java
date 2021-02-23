package com.projeto.academia.Projeto.Academia.api.formaPagamento.model.assembler;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.utils.model.assembler.AbstractAssemblerDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoAssembler extends AbstractAssemblerDTO<FormaPagamento, FormaPagamentoDTO> {

    @Override
    public FormaPagamentoDTO entidadeParaDTO(@NonNull FormaPagamento formaPagamento) {

        return FormaPagamentoDTO.builder()
                .id(formaPagamento.getId())
                .tipoPlanoPagamento(formaPagamento.getTipoPlanoPagamento())
                .porcentagemDesconto(formaPagamento.getPorcentagemDesconto())
                .valorComDesconto(formaPagamento.getValorComDesconto())
                .valorSemDesconto(formaPagamento.getValorSemDesconto())
                .build();
    }

    @Override
    public FormaPagamento dtoParaEntidade(@NonNull FormaPagamentoDTO formaPagamentoDTO) {
        return FormaPagamento.builder()
                .id(formaPagamentoDTO.getId())
                .porcentagemDesconto(formaPagamentoDTO.getPorcentagemDesconto())
                .tipoPlanoPagamento(formaPagamentoDTO.getTipoPlanoPagamento())
                .valorComDesconto(formaPagamentoDTO.getValorComDesconto())
                .valorSemDesconto(formaPagamentoDTO.getValorSemDesconto())
                .build();
    }
}
