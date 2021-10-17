package com.projeto.academia.Projeto.Academia.utils.calculator;

import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import com.projeto.academia.Projeto.Academia.api.services.impl.PaymentAmountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentCalculator {

    @Autowired
    private static PaymentAmountsService paymentAmountsService;

    public static double calculatePaymentAmount(PaymentPlanType paymentPlanType) {
        return paymentAmountsService.getPaymentAmounts(paymentPlanType);
    }

    public static double calculateDiscountedAmount(RegistrationDTO registrationDTO) {
        double percentage = registrationDTO.getPorcentagemDesconto();
        return calculatesDiscountAmount(registrationDTO, percentage);
    }

    private static double calculatesDiscountAmount(RegistrationDTO registrationDTO, double percentage) {
        double discount = registrationDTO.getValorSemDesconto();
        if (percentage > 0.0 && percentage < 100) {
            discount = registrationDTO.getValorSemDesconto() - registrationDTO.getValorSemDesconto() * (registrationDTO.getPorcentagemDesconto() / 100);
        }
        return discount;
    }
}