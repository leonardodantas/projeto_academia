package com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento;

public enum TipoPlanoPagamento {

    MENSAL("MENSAL"), SEMESTRAL("SEMESTRAL"), ANUAL("ANUAL");

    private String tipoPlanoPagamento;

    TipoPlanoPagamento(String tipoPlanoPagamento){
        this.tipoPlanoPagamento = tipoPlanoPagamento;
    }

    public String getTipoPlanoPagamento(){
        return this.tipoPlanoPagamento;
    }

}


