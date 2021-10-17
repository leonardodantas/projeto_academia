package com.projeto.academia.Projeto.Academia.api.repositorys.formPayment;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentMethod;

public interface IPaymentMethodRepository {
    PaymentMethod save(PaymentMethod paymentMethod);
}
