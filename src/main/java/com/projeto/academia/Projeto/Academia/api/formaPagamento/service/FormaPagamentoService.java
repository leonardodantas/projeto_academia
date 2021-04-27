package com.projeto.academia.Projeto.Academia.api.formaPagamento.service;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.assembler.FormaPagamentoAssembler;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.repository.IFormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private IFormaPagamentoRepository iFormaPagamentoRepository;

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    public FormaPagamento salvarFormaPagamento(FormaPagamento formaPagamento){
        return this.salvarFormaPagamentoNoBanco(formaPagamento);
    }

    private FormaPagamento salvarFormaPagamentoNoBanco(FormaPagamento formaPagamento){
        FormaPagamento formaPagamentoSalva;
        try {
            formaPagamentoSalva =  iFormaPagamentoRepository.save(formaPagamento);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return formaPagamento;
    }
}
