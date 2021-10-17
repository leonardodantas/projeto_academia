package com.projeto.academia.Projeto.Academia.api.repositorys.paymentAmounts;

import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentAmounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentAmountsRepository extends JpaRepository<PaymentAmounts, String> {
    Optional<PaymentAmounts> findByPaymentPlanType(PaymentPlanType tipoPagamento);
}
