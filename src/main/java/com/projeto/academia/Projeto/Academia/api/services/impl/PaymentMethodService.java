package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentMethod;
import com.projeto.academia.Projeto.Academia.api.repositorys.formPayment.IPaymentMethodRepository;
import com.projeto.academia.Projeto.Academia.api.services.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    private IPaymentMethodRepository formPaymentRepository;

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod){
        return formPaymentRepository.save(paymentMethod);
    }

}
