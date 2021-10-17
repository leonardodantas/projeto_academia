package com.projeto.academia.Projeto.Academia.api.models.dto;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class RegistrationDTO implements DataTransferObject {

    @ApiModelProperty(value = "Id do Cadastro", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Id do Aluno", name = "idAluno", dataType = "String", example = "ahnb594aa41fd98q5")
    private String idStudent;

    @ApiModelProperty(value = "Id da avaliação", name = "formaPagamento", dataType = "FormaPagamentoDTO", example = "FormaPagamentoDTO.class")
    private PaymentMethod paymentMethod;

    private RegistrationDTO(Registration registration) {
        this.id = registration.getId();
        this.paymentMethod = PaymentMethod.from(registration.getPaymentMethod());
        this.idStudent = registration.getIdStudent();
    }

    public static RegistrationDTO from(Registration registration) {
        return new RegistrationDTO(registration);
    }

    public PaymentPlanType getTipoPlanoPagamento(){
        return this.getPaymentMethod().getPaymentPlanType();
    }

    public double getPorcentagemDesconto(){
        return this.getPaymentMethod().getPorcentagemDesconto();
    }

    public double getValorSemDesconto(){
        return this.getPaymentMethod().getValorSemDesconto();
    }

}
