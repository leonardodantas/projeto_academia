package com.projeto.academia.Projeto.Academia.api.repositorys.registration;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, String> {
}
