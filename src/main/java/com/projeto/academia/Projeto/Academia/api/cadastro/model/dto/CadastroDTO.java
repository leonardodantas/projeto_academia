package com.projeto.academia.Projeto.Academia.api.cadastro.model.dto;

import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroDTO implements DataTransferObject {

    @ApiModelProperty(value = "Id do Cadastro", name = "id", dataType = "String", example = "ahnb594aa41fd98q5")
    private String id;

    @ApiModelProperty(value = "Id do Aluno", name = "idAluno", dataType = "String", example = "ahnb594aa41fd98q5")
    private String idAluno;

    @ApiModelProperty(value = "Id da avaliação", name = "formaPagamento", dataType = "FormaPagamentoDTO", example = "FormaPagamentoDTO.class")
    private FormaPagamentoDTO formaPagamento;

}
