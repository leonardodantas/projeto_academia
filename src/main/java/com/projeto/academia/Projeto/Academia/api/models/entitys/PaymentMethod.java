package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.utils.calculator.PaymentCalculator;
import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Table(name = "metodo_pagamento")
@javax.persistence.Entity(name = "FORMA_PAGAMENTO")
public class PaymentMethod extends Entity {

    @Id
    @Column(length = 20)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano_pagamento")
    private PaymentPlanType paymentPlanType;

    @Column(name = "porcentagem_desconto")
    private double discountPercentage;

    @Column(name = "valor_sem_desconto")
    private double valueWithoutDiscount;

    @Column(name = "valor_com_desconto")
    private double discountedValue;

    private PaymentMethod(RegistrationDTO registration) {
        this.id = UUID.randomUUID().toString();
        this.paymentPlanType = registration.getTipoPlanoPagamento();
        this.discountPercentage = registration.getPorcentagemDesconto();
        this.discountedValue = PaymentCalculator.calculateDiscountedAmount(registration);
        this.valueWithoutDiscount = PaymentCalculator.calculatePaymentAmount(registration.getTipoPlanoPagamento());
    }

    private PaymentMethod(RegistrationDTO registrationDTO, double newPercentage) {
        this.id = registrationDTO.getId();
        this.paymentPlanType = registrationDTO.getTipoPlanoPagamento();
        this.discountPercentage = newPercentage;
        this.discountedValue = PaymentCalculator.calculateDiscountedAmount(registrationDTO);
        this.valueWithoutDiscount = PaymentCalculator.calculatePaymentAmount(registrationDTO.getTipoPlanoPagamento());
    }

    public static PaymentMethod from(RegistrationDTO registration) {
        return new PaymentMethod(registration);
    }

    public static PaymentMethod from(RegistrationDTO registrationDTO, double newPercentage) {
        return new PaymentMethod(registrationDTO, newPercentage);
    }
}
