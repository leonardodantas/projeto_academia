package com.projeto.academia.Projeto.Academia.api.repositorys.formPayment.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentMethod;
import com.projeto.academia.Projeto.Academia.api.repositorys.formPayment.PaymentMethodRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.formPayment.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentMethodMethodImpl implements IPaymentMethodRepository {

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        try {
            return paymentMethodRepository.save(paymentMethod);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
