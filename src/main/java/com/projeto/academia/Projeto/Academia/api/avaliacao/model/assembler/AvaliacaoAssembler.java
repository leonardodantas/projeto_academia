package com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.utils.model.assembler.AbstractAssemblerDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoAssembler extends AbstractAssemblerDTO<Avaliacao, AvaliacaoDTO> {

    @Override
    public AvaliacaoDTO entidadeParaDTO(@NonNull Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .idAluno(avaliacao.getIdAluno())
                .dataAvaliacao(avaliacao.getDataAvaliacao())
                .altura(avaliacao.getAltura())
                .peso(avaliacao.getPeso())
                .imc(avaliacao.getImc())
                .dataAtualizacaoAvaliacao(avaliacao.getDataAtualizacaoAvaliacao())
                .build();
    }

    @Override
    public Avaliacao dtoParaEntidade(@NonNull AvaliacaoDTO avaliacaoDTO) {
        return Avaliacao.builder()
                .id(avaliacaoDTO.getId())
                .idAluno(avaliacaoDTO.getIdAluno())
                .altura(avaliacaoDTO.getAltura())
                .dataAvaliacao(avaliacaoDTO.getDataAvaliacao())
                .imc(avaliacaoDTO.getImc())
                .peso(avaliacaoDTO.getPeso())
                .dataAtualizacaoAvaliacao(avaliacaoDTO.getDataAtualizacaoAvaliacao())
                .build();
    }
}
