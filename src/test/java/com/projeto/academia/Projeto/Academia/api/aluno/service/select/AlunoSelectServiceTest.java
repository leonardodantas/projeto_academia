package com.projeto.academia.Projeto.Academia.api.aluno.service.select;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlunoSelectServiceTest {

    @InjectMocks
    private AlunoSelectService alunoSelectService;

    @Mock
    private IAlunoRepository iAlunoRepository;

    @Mock
    private AlunoAssembler alunoAssembler;

    @Mock
    private Pageable pageable;

    @Test
    public void deveRecuperarTodosOsAlunos(){
        Page<Aluno> alunoRecuperados = gerarListaDeAlunos();
        List<AlunoDTO> alunosDTO = gerarListaAlunosDTO();
        when(iAlunoRepository.findAll(Matchers.isA(Pageable.class))).thenReturn(alunoRecuperados);
        when(alunoAssembler.muitasEntidadesParaMuitosDTOs(alunoRecuperados.getContent())).thenReturn(alunosDTO);
        CollectionResponse<AlunoDTO, Aluno> alunos = alunoSelectService.recuperarTodos(pageable);
        assertEquals(alunos.getSize(), 5);
    }

    @Test
    public void deveRecuperarAlunoPorCPF(){
        String cpf = "45678912345";

         Aluno aluno = Aluno.builder()
                .id("123")
                .cpf(cpf)
                .build();

         AlunoDTO alunoDTO = AlunoDTO.builder()
                 .id("123")
                 .cpf(cpf)
                 .build();
         when(alunoAssembler.entidadeParaDTO(aluno)).thenReturn(alunoDTO);
        when(iAlunoRepository.findByCpf(cpf)).thenReturn(Optional.of(aluno));
        AlunoDTO alunoRecuperado = alunoSelectService.recuperaAlunoPorCPF(cpf);

        assertEquals(aluno.getCpf(), alunoRecuperado.getCpf());
    }

    @Test
    public void naoDeveRecuperarAlunoPorCPF(){
        String cpf = "45678912345";

        Aluno aluno = Aluno.builder()
                .id("123")
                .cpf(cpf)
                .build();

        AlunoDTO alunoRecuperado = alunoSelectService.recuperaAlunoPorCPF("123456");

        Assertions.assertNull(alunoRecuperado.getId());
    }


    private List<AlunoDTO> gerarListaAlunosDTO() {
        List<AlunoDTO> alunos = new ArrayList<>();

        int i = 0;
        while (i < 5){
            AlunoDTO aluno = AlunoDTO.builder()
                    .id(String.valueOf(i))
                    .build();
            i ++;
            alunos.add(aluno);
        }
        return alunos;
    }

    private Page<Aluno> gerarListaDeAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        int i = 0;
        while (i < 5){
            Aluno aluno = Aluno.builder()
                    .id(String.valueOf(i))
                    .build();
            i ++;
            alunos.add(aluno);
        }
        Page<Aluno> alunoPage = new PageImpl<>(alunos);
        return alunoPage;
    }
}
