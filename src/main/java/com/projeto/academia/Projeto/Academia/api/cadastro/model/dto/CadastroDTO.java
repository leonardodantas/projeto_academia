package com.projeto.academia.Projeto.Academia.api.cadastro.model.dto;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroDTO implements DataTransferObject {

    @Setter
    @ApiModelProperty(value = "Id do Cadastro", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Id do Aluno", name = "idAluno", dataType = "String", example = "ahnb594aa41fd98q5")
    private String idAluno;

    @ApiModelProperty(value = "Id da avaliação", name = "formaPagamento", dataType = "FormaPagamentoDTO", example = "FormaPagamentoDTO.class")
    private FormaPagamentoDTO formaPagamento;

    public TipoPlanoPagamento getTipoPlanoPagamento(){
        return this.getFormaPagamento().getTipoPlanoPagamento();
    }

    public double getPorcentagemDesconto(){
        return this.getFormaPagamento().getPorcentagemDesconto();
    }

    public double getValorSemDesconto(){
        return this.getFormaPagamento().getValorSemDesconto();
    }

    public void setPorcentagemDesconto(double porcentagemDesconto){
        this.getFormaPagamento().setPorcentagemDesconto(porcentagemDesconto);
    }

    public void setValorSemDesconto(double valorSemDesconto){
        this.getFormaPagamento().setValorSemDesconto(valorSemDesconto);
    }

    public void setValorComDesconto(double valorComDesconto){
        this.getFormaPagamento().setValorComDesconto(valorComDesconto);
    }

    public void gerarIdCadastroEIdFormaPagamento() {
        this.id = GeradorID.getInstance().gerarCodigo();
        this.getFormaPagamento().setId(GeradorID.getInstance().gerarCodigo());
    }
}
