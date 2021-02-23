package com.projeto.academia.Projeto.Academia.api.formaPagamento.model;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Builder
@Getter

@javax.persistence.Entity(name = "FORMA_PAGAMENTO")
public class FormaPagamento extends Entity {

    @Id
    @Column(length = 20)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano_pagamento")
    private TipoPlanoPagamento tipoPlanoPagamento;

    @Column(name = "porcentagem_desconto")
    private double porcentagemDesconto;

    @Column(name = "valor_sem_desconto")
    private double valorSemDesconto;

    @Column(name = "valor_com_desconto")
    private double valorComDesconto;
}
