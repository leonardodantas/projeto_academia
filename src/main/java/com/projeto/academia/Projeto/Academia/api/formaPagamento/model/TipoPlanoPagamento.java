package com.projeto.academia.Projeto.Academia.api.formaPagamento.model;

public enum TipoPlanoPagamento {

    MENSAL("MENSAL"), TRIMESTRAL("TRIMESTRAL"), SEMESTRAL("SEMESTRAL"), ANUAL("ANUAL");

    private String tipoPlanoPagamento;

    TipoPlanoPagamento(String tipoPlanoPagamento){
        this.tipoPlanoPagamento = tipoPlanoPagamento;
    }

    public String getTipoPlanoPagamento(){
        return this.tipoPlanoPagamento;
    }
}
