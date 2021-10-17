package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;

public interface IPaymentAmountsService {

    double getPaymentAmounts(PaymentPlanType paymentPlanType);
}
