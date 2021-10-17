package com.projeto.academia.Projeto.Academia.api.models.dto;

import com.projeto.academia.Projeto.Academia.utils.enums.PaymentPlanType;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod implements DataTransferObject {

    @Setter
    @ApiModelProperty(value = "Id da Forma de Pagamento", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Enum do Tipo de Pagamento", name = "tipoPlanoPagamento", dataType = "TipoPlanoPagamento", example = "MENSAL")
    @NotNull(message = "{not.null}")
    private PaymentPlanType paymentPlanType;

    @Setter
    @ApiModelProperty(value = "Porcentagem para desconto", name = "porcentagemDesconto", dataType = "double", example = "12.5")
    private double porcentagemDesconto;

    @Setter
    @ApiModelProperty(value = "Valor sem desconto", name = "valorSemDesconto", dataType = "double", example = "200", hidden = true)
    private double valorSemDesconto;

    @Setter
    @ApiModelProperty(value = "Valor com Desconto", name = "valorComDesconto", dataType = "double", example = "178", hidden = true)
    private double valorComDesconto;

    private PaymentMethod(com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.paymentPlanType = paymentMethod.getPaymentPlanType();
        this.porcentagemDesconto = paymentMethod.getDiscountPercentage();
        this.valorComDesconto = paymentMethod.getDiscountedValue();
        this.valorSemDesconto = paymentMethod.getValueWithoutDiscount();
    }

    public static PaymentMethod from(com.projeto.academia.Projeto.Academia.api.models.entitys.PaymentMethod paymentMethod) {
        return new PaymentMethod(paymentMethod);
    }
}
