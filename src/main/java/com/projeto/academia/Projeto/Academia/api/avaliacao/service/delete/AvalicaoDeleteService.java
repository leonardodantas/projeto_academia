package com.projeto.academia.Projeto.Academia.api.avaliacao.service.delete;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.select.AvaliacaoSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AvalicaoDeleteService {

    @Autowired
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Autowired
    private AvaliacaoSelectService avaliacaoSelectService;

    @Autowired
    private AlunoSelectService alunoSelectService;

    @Autowired
    private AvaliacaoAssembler avaliacaoAssembler;

    public AvaliacaoDTO removerAvaliacao(String idAvaliacao){

        AvaliacaoDTO avaliacaoDTO = avaliacaoSelectService.recuperarAvaliacaoPeloID(idAvaliacao);

        if (Strings.isNullOrEmpty(avaliacaoDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID para a avaliação não existe");
        }

        this.removerAvaliacaoNoBanco(idAvaliacao);

        return avaliacaoDTO;
    }

    public AlunoDTO removerTodasAsAvaliacoesDoAluno(String idAluno){

        AlunoDTO alunoDTO = alunoSelectService.recuperarAlunoETodasAsAvalicoes(idAluno);
        if (Strings.isNullOrEmpty(alunoDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não existe na base de dados de alunos");
        }

        if (Objects.isNull(alunoDTO.getAvaliacoes())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não possui nenhuma avaliação");
        }

        this.removerTodasAvalicoesDeAlunoNoBanco(alunoDTO.getId());

        return alunoDTO;
    }

    private void removerTodasAvalicoesDeAlunoNoBanco(String idAluno){
        try {
            iAvaliacaoRepository.deleteAllByIdAluno(idAluno);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void removerAvaliacaoNoBanco(String idAvaliacao){
        try {
            iAvaliacaoRepository.deleteById(idAvaliacao);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
