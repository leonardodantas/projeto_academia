package com.projeto.academia.Projeto.Academia.api.repositorys.paymentAmounts;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentAmounts;
import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;

import java.util.Optional;

public interface IPaymentAmountsRepository  {
    Optional<PaymentAmounts> findByPaymentAmounts(PaymentPlanType paymentAmounts);
    Optional<PaymentAmounts> paymentPlanType(PaymentPlanType paymentPlanType);
}
