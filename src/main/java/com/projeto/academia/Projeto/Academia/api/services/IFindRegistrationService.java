package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.data.domain.Pageable;

public interface IFindRegistrationService {

    RegistrationDTO findById(String idRegistration);
    RegistrationDTO throwExceptionNotExistRegistrationById(String idRegistration);
    CollectionResponse<RegistrationDTO, Registration> findAll(Pageable pageable);
}
