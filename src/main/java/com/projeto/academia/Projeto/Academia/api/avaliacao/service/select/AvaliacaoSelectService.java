package com.projeto.academia.Projeto.Academia.api.avaliacao.service.select;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoSelectService {

    @Autowired
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Autowired
    private AvaliacaoAssembler avaliacaoAssembler;

    public CollectionResponse<AvaliacaoDTO, Avaliacao> recuperarTodos(Pageable pageable){
        Page<Avaliacao> avaliacoesPage = this.recuperarPaginaDeAlunosDoBanco(pageable);
        List<AvaliacaoDTO> avaliacoesDTO = avaliacaoAssembler.muitasEntidadesParaMuitosDTOs(avaliacoesPage.getContent());
        CollectionResponse<AvaliacaoDTO, Avaliacao> avaliacaoResponseDTO = new CollectionResponse<>(avaliacoesPage, avaliacoesDTO);
        return avaliacaoResponseDTO;
    }

    public CollectionResponse<AvaliacaoDTO, Avaliacao> recuperarTodasAvaliacoesDeAluno(String idAluno, Pageable pageable){
        Page<Avaliacao> avaliacoesPage = this.recuperarTodasAvaliacoesDeAlunoDoBanco(idAluno, pageable);
        List<AvaliacaoDTO> avaliacoesDTO = avaliacaoAssembler.muitasEntidadesParaMuitosDTOs(avaliacoesPage.getContent());
        CollectionResponse<AvaliacaoDTO, Avaliacao> avaliacaoResponseDTO = new CollectionResponse<>(avaliacoesPage, avaliacoesDTO);
        return avaliacaoResponseDTO;
    }

    public AvaliacaoDTO recuperarAvaliacaoPeloID(String idAvaliacao){
        AvaliacaoDTO avaliacaoDTO = this.recuperarAvaliacaoPeloIDRecuperarDoBanco(idAvaliacao);
        return  avaliacaoDTO;
    }

    public List<AvaliacaoDTO> recuperarListaDeAvaliacoesPeloIDAluno(String idUsario){
        List<Avaliacao> avaliacaoList = iAvaliacaoRepository.findAllByIdAluno(idUsario);
        List<AvaliacaoDTO> avaliacaoDTOList = avaliacaoAssembler.muitasEntidadesParaMuitosDTOs(avaliacaoList);
        return avaliacaoDTOList;
    }

    private AvaliacaoDTO recuperarAvaliacaoPeloIDRecuperarDoBanco(String idAvaliacao){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        try {
            Optional<Avaliacao> avaliacao = iAvaliacaoRepository.findById(idAvaliacao);
            if (avaliacao.isPresent()){
                avaliacaoDTO = avaliacaoAssembler.entidadeParaDTO(avaliacao.get());
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return avaliacaoDTO;
    }

    private Page<Avaliacao> recuperarTodasAvaliacoesDeAlunoDoBanco(String idAluno, Pageable pageable) {
        Page<Avaliacao> avaliacaos = Page.empty();
        try {
            avaliacaos = iAvaliacaoRepository.findAllByIdAluno(idAluno, pageable);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return avaliacaos;
    }

    private Page<Avaliacao> recuperarPaginaDeAlunosDoBanco(Pageable pageable) {
        Page<Avaliacao> avaliacaos = Page.empty();
        try {
            avaliacaos = iAvaliacaoRepository.findAll(pageable);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return avaliacaos;
    }

    //recuperar ultima avaliação atualizada de aluno

    public AvaliacaoDTO recuperarUltimaAvaliacaoDoAluno(String idAluno){
        AvaliacaoDTO avaliacaoDTO = this.recuperarUltimaAvaliacaoDoAlunoNoBanco(idAluno);
        return avaliacaoDTO;
    }

    private AvaliacaoDTO recuperarUltimaAvaliacaoDoAlunoNoBanco(String idAluno){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        try {
            Optional<Avaliacao> avaliacao = iAvaliacaoRepository.findFirstByIdAlunoOrderByDataAvaliacaoDesc(idAluno);
            if (avaliacao.isPresent()){
                avaliacaoDTO = avaliacaoAssembler.entidadeParaDTO(avaliacao.get());
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return avaliacaoDTO;
    }


    public AvaliacaoDTO recuperarUltimaAvaliacaoDoAlunoAtualizada(String idAluno) {
        AvaliacaoDTO avaliacaoDTO = this.recuperarUltimaAvaliacaoAtualizadaDoAlunoNoBanco(idAluno);
        return avaliacaoDTO;
    }

    private AvaliacaoDTO recuperarUltimaAvaliacaoAtualizadaDoAlunoNoBanco(String idAluno) {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        try {
            Optional<Avaliacao> avaliacao = iAvaliacaoRepository.findFirstByIdAlunoOrderByDataAtualizacaoAvaliacaoDesc(idAluno);
            if (avaliacao.isPresent()){
                avaliacaoDTO = avaliacaoAssembler.entidadeParaDTO(avaliacao.get());
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return avaliacaoDTO;
    }
}
