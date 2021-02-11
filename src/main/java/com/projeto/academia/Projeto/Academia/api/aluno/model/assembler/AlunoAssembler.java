package com.projeto.academia.Projeto.Academia.api.aluno.model.assembler;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.generico.model.assembler.AbstractAssemblerDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AlunoAssembler extends AbstractAssemblerDTO<Aluno, AlunoDTO> {

    @Autowired
    private AvaliacaoAssembler avaliacaoAssembler;

    @Override
    public AlunoDTO entidadeParaDTO(@NonNull Aluno aluno) {

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();
        if (!Objects.isNull(aluno.getAvaliacoes())) {
            avaliacaoDTOS = avaliacaoAssembler.muitasEntidadesParaMuitosDTOs(aluno.getAvaliacoes());
        }

        return AlunoDTO.builder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .email(aluno.getEmail())
                .nome(aluno.getNome())
                .numeroCelular(aluno.getNumeroCelular())
                .avaliacoes(avaliacaoDTOS)
                .build();
    }

    @Override
    public Aluno dtoParaEntidade(@NonNull AlunoDTO alunoDTO) {

        List<Avaliacao> avaliacaos = new ArrayList<>();
        if (!Objects.isNull(alunoDTO.getAvaliacoes())) {
            avaliacaos = avaliacaoAssembler.muitosDTOsParaMuitasEntidade(alunoDTO.getAvaliacoes());
        }

        return Aluno.builder()
                .id(alunoDTO.getId())
                .avaliacoes(avaliacaos)
                .cpf(alunoDTO.getCpf())
                .email(alunoDTO.getEmail())
                .nome(alunoDTO.getNome())
                .numeroCelular(alunoDTO.getNumeroCelular())
                .build();
    }
}
