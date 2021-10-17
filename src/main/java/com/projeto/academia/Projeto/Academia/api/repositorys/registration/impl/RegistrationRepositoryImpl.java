package com.projeto.academia.Projeto.Academia.api.repositorys.registration.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.repositorys.registration.IRegistrationRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.registration.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class RegistrationRepositoryImpl implements IRegistrationRepository {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public Optional<Registration> findById(String idRegistration) {
        return registrationRepository.findById(idRegistration);
    }

    @Override
    public Page<Registration> findAll(Pageable pageable) {
        return registrationRepository.findAll(pageable);
    }

    @Override
    public void deleteById(String id) {
        try {
            registrationRepository.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Registration save(Registration registration) {
        try {
            return registrationRepository.save(registration);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
