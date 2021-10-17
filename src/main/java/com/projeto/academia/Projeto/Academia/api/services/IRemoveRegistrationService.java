package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;

public interface IRemoveRegistrationService {

    RegistrationDTO removeRegistration(String id);
}
