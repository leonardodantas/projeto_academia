package com.projeto.academia.Projeto.Academia.api.avaliacao.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.select.AvaliacaoSelectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvaliacaoInsertServiceTest {

    @InjectMocks
    private AvaliacaoInsertService avaliacaoInsertService;

    @Mock
    private AlunoSelectService alunoSelectService;

    @Mock
    private AvaliacaoAssembler avaliacaoAssembler;

    @Mock
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Mock
    private AvaliacaoSelectService avaliacaoSelectService;

    AvaliacaoDTO avaliacaoDTO;

    AlunoDTO alunoDTO;

    Avaliacao avaliacao;

    @Before
    public void init(){
        String idAluno = "123456";
        this.avaliacaoDTO = AvaliacaoDTO.builder()
                .idAluno(idAluno)
                .peso(85)
                .altura(1.75)
                .build();
        this.alunoDTO = AlunoDTO.builder()
                .id(idAluno)
                .cpf("123456789")
                .build();
        this.avaliacao = Avaliacao.builder()
                .idAluno(idAluno)
                .build();

        when(alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(avaliacaoDTO.getIdAluno())).thenReturn(alunoDTO);
        when(alunoSelectService.recuperaAlunoPorCPF(alunoDTO.getCpf())).thenReturn(alunoDTO);
        when(avaliacaoAssembler.dtoParaEntidade(avaliacaoDTO)).thenReturn(avaliacao);
        when(iAvaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        when(avaliacaoAssembler.entidadeParaDTO(avaliacao)).thenReturn(avaliacaoDTO);

    }

    @Test
    public void deveCriarUmaNovaAvaliacao(){

        String idAluno = "123456";

        AvaliacaoDTO avaliacaoDTOSalva = avaliacaoInsertService.criarAvaliacao(this.avaliacaoDTO);

        assertEquals(avaliacaoDTOSalva.getIdAluno(), idAluno);
        assertEquals(avaliacaoDTOSalva.getId().length(), 20);
        assertEquals(avaliacaoDTOSalva.getImc(), 27.76, 0.002);
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoPorNaoExitirAlunoComIdInformado(){

        String idAlunoInexistente = "123";
        AvaliacaoDTO avaliacao = AvaliacaoDTO.builder()
                .idAluno("123")
                .build();
        when(alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(idAlunoInexistente)).thenReturn(AlunoDTO.builder().build());
        avaliacaoInsertService.criarAvaliacao(avaliacao);
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoPorNaoExistirCPF(){
        String idAlunoInexistente = "123";
        String cpfInexistente = "456";
        AvaliacaoDTO avaliacao = AvaliacaoDTO.builder()
                .idAluno("123")
                .build();
        when(alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(idAlunoInexistente)).thenReturn(AlunoDTO.builder().id("123").cpf(cpfInexistente).build());
        when(alunoSelectService.recuperaAlunoPorCPF(cpfInexistente)).thenReturn(AlunoDTO.builder().build());

        avaliacaoInsertService.criarAvaliacao(avaliacao);
    }

    @Test
    public void deveAtualizarUmaAvaliacao(){
        AvaliacaoDTO avaliacaoDTO = AvaliacaoDTO.builder()
                .id("1")
                .idAluno("123456")
                .altura(1.75)
                .peso(85)
                .build();


        when(avaliacaoSelectService.recuperarAvaliacaoPeloID(avaliacaoDTO.getId())).thenReturn(avaliacaoDTO);
        when(alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(avaliacaoDTO.getIdAluno())).thenReturn(alunoDTO);
        when(alunoSelectService.recuperaAlunoPorCPF(alunoDTO.getCpf())).thenReturn(alunoDTO);
        when(avaliacaoAssembler.dtoParaEntidade(avaliacaoDTO)).thenReturn(avaliacao);
        when(iAvaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        when(avaliacaoAssembler.entidadeParaDTO(avaliacao)).thenReturn(avaliacaoDTO);

        AvaliacaoDTO avaliacaoAtualizada = avaliacaoInsertService.atualizarAvaliacao(avaliacaoDTO);
        assertEquals(avaliacaoDTO.getId(), avaliacaoAtualizada.getId());
        assertEquals(avaliacaoAtualizada.getImc(), 27.76, 0.002);
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoEmAvaliacaoSemID(){
        avaliacaoInsertService.atualizarAvaliacao(AvaliacaoDTO.builder().build());
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoEmAvaliacaoInexistente(){
        String idAvaliacao = "123456";
        when(avaliacaoSelectService.recuperarAvaliacaoPeloID(idAvaliacao)).thenReturn(AvaliacaoDTO.builder().build());
        avaliacaoInsertService.atualizarAvaliacao(AvaliacaoDTO.builder().id(idAvaliacao).build());
    }

}
