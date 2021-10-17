package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.registration.IRegistrationRepository;
import com.projeto.academia.Projeto.Academia.api.services.IFindRegistrationService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindRegistrationService implements IFindRegistrationService {

    @Autowired
    private IRegistrationRepository registrationRepository;

    private static final String REGISTRATION_NOT_EXIST = "Cadastro não existe na base de dados";

    @Override
    public RegistrationDTO findById(String idRegistration){
        Optional<Registration> registration = registrationRepository.findById(idRegistration);
        return RegistrationDTO.from(registration.get());
    }

    @Override
    public RegistrationDTO throwExceptionNotExistRegistrationById(String idCadastro){
        Registration registration = registrationRepository.findById(idCadastro)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,REGISTRATION_NOT_EXIST));
        return RegistrationDTO.from(registration);
    }

    @Override
    public CollectionResponse<RegistrationDTO, Registration> findAll(Pageable pageable) {
        Page<Registration> cadastroPage = registrationRepository.findAll(pageable);
        List<RegistrationDTO> registrationDTOList = cadastroPage.getContent().stream().map(RegistrationDTO::from).collect(Collectors.toUnmodifiableList());
        return new CollectionResponse<>(cadastroPage, registrationDTOList);
    }
}
