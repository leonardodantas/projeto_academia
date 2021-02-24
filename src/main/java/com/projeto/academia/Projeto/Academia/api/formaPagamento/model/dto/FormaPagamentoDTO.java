package com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDTO implements DataTransferObject {

    @ApiModelProperty(value = "Id da Forma de Pagamento", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Enum do Tipo de Pagamento", name = "tipoPlanoPagamento", dataType = "TipoPlanoPagamento", example = "MENSAL")
    @NotNull(message = "{not.null}")
    private TipoPlanoPagamento tipoPlanoPagamento;

    @Setter
    @ApiModelProperty(value = "Porcentagem para desconto", name = "porcentagemDesconto", dataType = "double", example = "12.5")
    private double porcentagemDesconto;

    @Setter
    @ApiModelProperty(value = "Valor sem desconto", name = "valorSemDesconto", dataType = "double", example = "200", hidden = true)
    private double valorSemDesconto;

    @Setter
    @ApiModelProperty(value = "Valor com Desconto", name = "valorComDesconto", dataType = "double", example = "178", hidden = true)
    private double valorComDesconto;
}
