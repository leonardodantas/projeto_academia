package com.projeto.academia.Projeto.Academia.api.aluno.model.assembler;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.generico.model.assembler.AbstractAssemblerDTO;

public class AlunoAssembler extends AbstractAssemblerDTO<Aluno, AlunoDTO> {

    @Override
    public AlunoDTO entidadeParaDTO(Aluno aluno) {
        return AlunoDTO.builder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .email(aluno.getEmail())
                .nome(aluno.getNome())
                .numeroCelular(aluno.getNumeroCelular())
                .build();
    }

    @Override
    public Aluno dtoParaEntidade(AlunoDTO alunoDTO) {
        return null;
    }
}
