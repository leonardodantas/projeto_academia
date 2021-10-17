package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity(name = "VALORES_PAGAMENTO")
public class PaymentAmounts extends Entity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano_pagamento")
    private PaymentPlanType paymentPlanType;

    private double value;

    public PaymentAmounts(){}
}
