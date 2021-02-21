package com.projeto.academia.Projeto.Academia.api.avaliacao.service.delete;

import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.select.AvaliacaoSelectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvaliacaoDeleteServiceTest {

    @InjectMocks
    private AvalicaoDeleteService avaliacaoDeleteService;

    @Mock
    private AvaliacaoSelectService avaliacaoSelectService;

    @Mock
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Mock
    private AlunoSelectService alunoSelectService;

    AlunoDTO alunoDTO;

    @Before
    public void init(){
        String idAAluno = "123";

        this.alunoDTO = AlunoDTO.builder()
                .id(idAAluno)
                .avaliacoes(
                        Arrays.asList(
                                AvaliacaoDTO.builder().id("1").build(),
                                AvaliacaoDTO.builder().id("2").build()))
                .build();

        when(alunoSelectService.recuperarAlunoETodasAsAvalicoes(idAAluno)).thenReturn(this.alunoDTO);

    }

    @Test
    public void deveRemoverUmaAvaliacao(){
        String idAvaliacao = "123";
        when(avaliacaoSelectService.recuperarAvaliacaoPeloID(idAvaliacao)).thenReturn(AvaliacaoDTO.builder().id(idAvaliacao).build());
        avaliacaoDeleteService.removerAvaliacao(idAvaliacao);
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoPorNaoExistirAvaliacao(){
        String idAvaliacao = "123";
        when(avaliacaoSelectService.recuperarAvaliacaoPeloID(idAvaliacao)).thenReturn(AvaliacaoDTO.builder().build());
        avaliacaoDeleteService.removerAvaliacao(idAvaliacao);
    }

    @Test
    public void deveRemoverTodasAsAvaliacoesDeAluno(){
        String idAAluno = "123";
        avaliacaoDeleteService.removerTodasAsAvaliacoesDoAluno(idAAluno);
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoPorAlunoNaoExistir(){
        String idAluno = "1";
        when(alunoSelectService.recuperarAlunoETodasAsAvalicoes(idAluno)).thenReturn(AlunoDTO.builder().build());
        avaliacaoDeleteService.removerTodasAsAvaliacoesDoAluno(idAluno);
    }
    @Test(expected = Exception.class)
    public void deveLancarExcecaoPorAvaliacoesNaoExistirem(){
        String idAluno = "1";
        when(alunoSelectService.recuperarAlunoETodasAsAvalicoes(idAluno)).thenReturn(AlunoDTO.builder().id(idAluno).build());
        avaliacaoDeleteService.removerTodasAsAvaliacoesDoAluno(idAluno);
    }


}
