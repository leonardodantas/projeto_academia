package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.services.ICreateRegistrationService;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentService;
import com.projeto.academia.Projeto.Academia.api.services.ISaveRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRegistrationService implements ICreateRegistrationService {

    @Autowired
    private IFindStudentService findStudentService;

    @Autowired
    private ISaveRegistrationService saveRegistrationService;

    @Override
    public RegistrationDTO createRegistration(RegistrationDTO registrationDTO) {
        findStudentService.throwExceptionNotExistStudentById(registrationDTO.getIdStudent());
        Registration registration = Registration.from(registrationDTO);
        saveRegistrationService.save(registration);
        return registrationDTO;
    }
}
