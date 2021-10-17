package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.services.IFindRegistrationService;
import com.projeto.academia.Projeto.Academia.api.services.ISaveRegistrationService;
import com.projeto.academia.Projeto.Academia.api.services.IUpdateRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateRegistrationService implements IUpdateRegistrationService {

    @Autowired
    private IFindRegistrationService findRegistrationService;

    @Autowired
    private ISaveRegistrationService saveRegistrationService;

    private static final String PERCENTAGE_INVALID = "Porcentagem invalida";

    @Override
    public RegistrationDTO updatePercentage(String idRegistration, double newPercetage) {
        RegistrationDTO registrationDTO = findRegistrationService.throwExceptionNotExistRegistrationById(idRegistration);
        if (newPercetage > 0 && newPercetage < 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PERCENTAGE_INVALID);
        }
        Registration registration = Registration.from(registrationDTO, newPercetage);
        saveRegistrationService.save(registration);
        return registrationDTO;
    }
}
