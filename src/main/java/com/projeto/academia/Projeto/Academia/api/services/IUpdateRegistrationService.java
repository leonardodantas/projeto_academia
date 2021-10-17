package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;

public interface IUpdateRegistrationService {

    RegistrationDTO updatePercentage(String idRegistration, double newPercetage);
}
