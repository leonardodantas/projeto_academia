package com.projeto.academia.Projeto.Academia.api.cadastro.model;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Cadastro extends Entity {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 20, name = "id_aluno")
    private String idAluno;

    @OneToOne
    @JoinColumn(name = "id_forma_pagamento")
    private FormaPagamento formaPagamento;
}
