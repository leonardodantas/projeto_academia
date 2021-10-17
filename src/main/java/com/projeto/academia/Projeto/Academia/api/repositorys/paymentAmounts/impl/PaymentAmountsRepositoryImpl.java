package com.projeto.academia.Projeto.Academia.api.repositorys.paymentAmounts.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentAmounts;
import com.projeto.academia.Projeto.Academia.api.repositorys.paymentAmounts.IPaymentAmountsRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.paymentAmounts.PaymentAmountsRepository;
import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PaymentAmountsRepositoryImpl implements IPaymentAmountsRepository {

    @Autowired
    private PaymentAmountsRepository paymentAmountsRepository;

    @Override
    public Optional<PaymentAmounts> findByPaymentAmounts(PaymentPlanType paymentAmounts) {
        return paymentAmountsRepository.findByPaymentPlanType(paymentAmounts);
    }

    @Override
    public Optional<PaymentAmounts> paymentPlanType(PaymentPlanType paymentPlanType) {
        return paymentAmountsRepository.findByPaymentPlanType(paymentPlanType);
    }
}
