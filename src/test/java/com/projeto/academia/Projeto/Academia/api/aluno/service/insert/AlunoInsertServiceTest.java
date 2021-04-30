package com.projeto.academia.Projeto.Academia.api.aluno.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AlunoInsertServiceTest {

    @InjectMocks
    private AlunoInsertService alunoInsertService;

    @Mock
    private IAlunoRepository iAlunoRepository;

    @Mock
    private AlunoAssembler alunoAssembler;

    @Mock
    private AlunoSelectService alunoSelectService;

    @Test
    public void deveInserirAluno(){

        String cpf = "456.879.225-01";
        AlunoDTO alunoDTO = AlunoDTO.builder()
                .nome("Leonardo Rodrigues Dantas")
                .cpf(cpf)
                .numeroCelular("99999999")
                .email("leonardo@dantas.com.br")
                .build();

        AlunoDTO alunoComCPF = AlunoDTO.builder().build();

        Aluno alunoSalvoNoBanco = Aluno.builder()
                .nome("Leonardo Rodrigues Dantas")
                .cpf(cpf)
                .numeroCelular("99999999")
                .email("leonardo@dantas.com.br")
                .build();

        doReturn(alunoSalvoNoBanco).when(iAlunoRepository).save(alunoSalvoNoBanco);
        doReturn(alunoSalvoNoBanco).when(alunoAssembler).dtoParaEntidade(alunoDTO);
        doReturn(alunoDTO).when(alunoAssembler).entidadeParaDTO(alunoSalvoNoBanco);

        AlunoDTO alunoInserido = alunoInsertService.inserirAluno(alunoDTO);

        assertEquals(alunoDTO.getNome(),alunoInserido.getNome());
    }

    @Test(expected = Exception.class)
    public void deveLancarExceptionPoisCPFJaExiste(){

        String cpf = "456.879.225-01";
        AlunoDTO alunoDTO = AlunoDTO.builder()
                .nome("Leonardo Rodrigues Dantas")
                .cpf(cpf)
                .numeroCelular("99999999")
                .email("leonardo@dantas.com.br")
                .build();

        AlunoDTO alunoComCPF = AlunoDTO.builder()
                .id("123456789")
                .build();

        alunoInsertService.inserirAluno(alunoDTO);

    }
}
