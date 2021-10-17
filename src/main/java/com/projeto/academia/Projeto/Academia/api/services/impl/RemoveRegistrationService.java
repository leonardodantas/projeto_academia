package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.registration.IRegistrationRepository;
import com.projeto.academia.Projeto.Academia.api.services.IFindRegistrationService;
import com.projeto.academia.Projeto.Academia.api.services.IRemoveRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveRegistrationService implements IRemoveRegistrationService {

    @Autowired
    private IRegistrationRepository registrationRepository;

    @Autowired
    private IFindRegistrationService findRegistrationService;

    public RegistrationDTO removeRegistration(String id){
        RegistrationDTO registration = findRegistrationService.throwExceptionNotExistRegistrationById(id);
        registrationRepository.deleteById(id);
        return registration;
    }
}
