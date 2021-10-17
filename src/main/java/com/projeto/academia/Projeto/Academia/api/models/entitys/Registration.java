package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "cadastro")
@javax.persistence.Entity
public class Registration extends Entity {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 20, name = "id_aluno")
    private String idStudent;

    @OneToOne
    @JoinColumn(name = "id_forma_pagamento")
    private PaymentMethod paymentMethod;

    private Registration(RegistrationDTO registration) {
        this.id = registration.getId();
        this.idStudent = registration.getIdStudent();
        this.paymentMethod = PaymentMethod.from(registration);
    }

    private Registration(RegistrationDTO registrationDTO, double newPercentage) {
        this.id = registrationDTO.getId();
        this.idStudent = registrationDTO.getIdStudent();
        this.paymentMethod = PaymentMethod.from(registrationDTO,newPercentage);
    }

    public static Registration from(RegistrationDTO registration) {
        return new Registration(registration);
    }

    public static Registration from(RegistrationDTO registrationDTO, double newPercentage) {
        return new Registration(registrationDTO, newPercentage);
    }
}
