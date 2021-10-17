package com.projeto.academia.Projeto.Academia.utils.enums;

public enum PaymentPlanType {

    MONTHLY("MENSAL"), QUARTERLY("TRIMESTRAL"), SEMESTER("SEMESTRAL"), YEARLY("ANUAL");

    private String paymentPlanType;

    PaymentPlanType(String paymentPlanType){
        this.paymentPlanType = paymentPlanType;
    }

    public String getPaymentPlanType(){
        return this.paymentPlanType;
    }

}


