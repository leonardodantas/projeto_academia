package com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.assembler.FormaPagamentoAssembler;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.utils.model.assembler.AbstractAssemblerDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroAssembler extends AbstractAssemblerDTO<Cadastro, CadastroDTO> {

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @Override
    public CadastroDTO entidadeParaDTO(@NonNull Cadastro cadastro) {

        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoAssembler.entidadeParaDTO(cadastro.getFormaPagamento());

        return CadastroDTO.builder()
                .id(cadastro.getId())
                .idAluno(cadastro.getIdAluno())
                .formaPagamento(formaPagamentoDTO)
                .build();
    }

    @Override
    public Cadastro dtoParaEntidade(@NonNull CadastroDTO cadastroDTO) {

        FormaPagamento formaPagamento = formaPagamentoAssembler.dtoParaEntidade(cadastroDTO.getFormaPagamento());
        return Cadastro.builder()
                .id(cadastroDTO.getId())
                .formaPagamento(formaPagamento)
                .idAluno(cadastroDTO.getIdAluno())
                .build();
    }
}
