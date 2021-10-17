package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentAmounts;
import com.projeto.academia.Projeto.Academia.api.repositorys.paymentAmounts.IPaymentAmountsRepository;
import com.projeto.academia.Projeto.Academia.api.services.IPaymentAmountsService;
import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentAmountsService implements IPaymentAmountsService {

    @Autowired
    private IPaymentAmountsRepository paymentAmountsRepository;

    public double getPaymentAmounts(PaymentPlanType paymentPlanType){
        Optional<PaymentAmounts> paymentAmounts = paymentAmountsRepository.paymentPlanType(paymentPlanType);
        return paymentAmounts.get().getValue();
    }
}
