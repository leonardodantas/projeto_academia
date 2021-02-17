package com.projeto.academia.Projeto.Academia.api.avaliacao.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
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

    AvaliacaoDTO avaliacaoDTO;

    @Before
    public void init(){
        String idAluno = "123456";
        this.avaliacaoDTO = AvaliacaoDTO.builder()
                .idAluno(idAluno)
                .peso(85)
                .altura(1.75)
                .build();
        AlunoDTO alunoDTO = AlunoDTO.builder()
                .id(idAluno)
                .cpf("123456789")
                .build();
        Avaliacao avaliacao = Avaliacao.builder()
                .idAluno(idAluno)
                .build();

        when(alunoSelectService.recuperarAlunoPorId(avaliacaoDTO.getIdAluno())).thenReturn(alunoDTO);
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

        AvaliacaoDTO avaliacao = AvaliacaoDTO.builder()
                .idAluno("123")
                .build();
        avaliacaoInsertService.criarAvaliacao(avaliacao);
    }

}
