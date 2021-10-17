package com.projeto.academia.Projeto.Academia.api.repositorys.formPayment;

import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
}
