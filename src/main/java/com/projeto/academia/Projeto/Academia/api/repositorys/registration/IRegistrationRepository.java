package com.projeto.academia.Projeto.Academia.api.repositorys.registration;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IRegistrationRepository {
    Optional<Registration> findById(String idRegistration);
    Page<Registration> findAll(Pageable pageable);
    void deleteById(String id);
    Registration save(Registration registration);
}
