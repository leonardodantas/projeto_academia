package com.projeto.academia.Projeto.Academia.api.avaliacao.service.select;

import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvaliacaoSelectServiceTest {

    @InjectMocks
    private AvaliacaoSelectService avaliacaoSelectService;

    @Mock
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Mock
    private AvaliacaoAssembler avaliacaoAssembler;

    @Mock
    private Pageable pageable;

    @Before
    public void init(){
        Page<Avaliacao> avaliacaoPage = this.gerarPageableAvaliacoes();
        List<AvaliacaoDTO> avaliacaoDTOList = this.listaDeAvalicoesDTO();
        when(iAvaliacaoRepository.findAll(Matchers.isA(Pageable.class))).thenReturn(avaliacaoPage);
        when(avaliacaoAssembler.muitasEntidadesParaMuitosDTOs(avaliacaoPage.getContent())).thenReturn(avaliacaoDTOList);

        String idAvaliacao = "1234";
        Avaliacao avaliacao = Avaliacao.builder()
                .id(idAvaliacao)
                .build();

        AvaliacaoDTO avaliacaoDTO = AvaliacaoDTO.builder()
                .id(idAvaliacao)
                .build();
        when(iAvaliacaoRepository.findById(idAvaliacao)).thenReturn(Optional.of(avaliacao));
        when(avaliacaoAssembler.entidadeParaDTO(avaliacao)).thenReturn(avaliacaoDTO);

    }

    @Test
    public void deveRetornarCollectionDeAvaliacoes(){
        CollectionResponse<AvaliacaoDTO,Avaliacao> response = avaliacaoSelectService.recuperarTodos(pageable);
        Assert.assertEquals(response.getSize(), 5);
    }

    @Test
    public void deveRetornarTodasAsAvalicoesPeloIDDoAluno(){
        Page<Avaliacao> avaliacaoPage = this.gerarPageableAvaliacoes();
        when(iAvaliacaoRepository.findAllByIdAluno(anyString(), Matchers.isA(Pageable.class))).thenReturn(avaliacaoPage);
        CollectionResponse<AvaliacaoDTO,Avaliacao> response = avaliacaoSelectService.recuperarTodasAvaliacoesDeAluno("132", pageable);
        Assert.assertEquals(response.getSize(), 5);
    }

    @Test
    public void deveRecuperarAvaliacaoPeloID(){
        String idAvaliacao = "1234";
        AvaliacaoDTO dto = avaliacaoSelectService.recuperarAvaliacaoPeloID(idAvaliacao);
        assertEquals(dto.getId(), idAvaliacao);
    }

    @Test
    public void naoDeveRecuperarAvaliacao(){
        String idAvaliacao = "12";
        AvaliacaoDTO dto = avaliacaoSelectService.recuperarAvaliacaoPeloID(idAvaliacao);
        assertNull(dto.getId());
    }


    private Page<Avaliacao> gerarPageableAvaliacoes(){
        List<Avaliacao> avaliacaoList = new ArrayList<>();
        for (int i =0; i < 5; i++){
            Avaliacao avaliacao = Avaliacao.builder()
                    .id(String.valueOf(i + 1))
                    .build();
            avaliacaoList.add(avaliacao);
        }
        Page<Avaliacao> avaliacaos = new PageImpl<>(avaliacaoList);
        return avaliacaos;
    }

    private List<AvaliacaoDTO> listaDeAvalicoesDTO(){
        List<AvaliacaoDTO> avaliacaoList = new ArrayList<>();
        for (int i =0; i < 5; i++){
            AvaliacaoDTO avaliacao = AvaliacaoDTO.builder()
                    .id(String.valueOf(i + 1))
                    .build();
            avaliacaoList.add(avaliacao);
        }
        return avaliacaoList;
    }
}
