package com.projeto.academia.Projeto.Academia.api.valoresPagamento.model;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Getter;

import javax.persistence.*;

@javax.persistence.Entity(name = "VALORES_PAGAMENTO")
public class ValoresPagamento extends Entity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano_pagamento")
    private TipoPlanoPagamento tipoPlanoPagamento;

    @Getter
    private double valor;

    public ValoresPagamento(){}
}
