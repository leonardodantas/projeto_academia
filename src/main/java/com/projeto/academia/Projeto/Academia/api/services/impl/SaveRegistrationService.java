package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.repositorys.registration.IRegistrationRepository;
import com.projeto.academia.Projeto.Academia.api.services.IPaymentMethodService;
import com.projeto.academia.Projeto.Academia.api.services.ISaveRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveRegistrationService implements ISaveRegistrationService {

    @Autowired
    private IRegistrationRepository registrationRepository;

    @Autowired
    private IPaymentMethodService paymentMethodService;

    @Override
    public void save(Registration registration) {
        paymentMethodService.save(registration.getPaymentMethod());
        registrationRepository.save(registration);
    }
}
