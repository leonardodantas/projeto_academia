package com.projeto.academia.Projeto.Academia.api.aluno.service.select;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoSelectService {

    @Autowired
    private AlunoAssembler alunoAssembler;

    @Autowired
    private IAlunoRepository iAlunoRepository;

    public CollectionResponse<AlunoDTO,Aluno> recuperarTodos(Pageable pageable) {
        Page<Aluno> alunos = recuperarListaDeAlunosDTODoBanco(pageable);
        List<AlunoDTO> alunoDTOS = alunoAssembler.muitasEntidadesParaMuitosDTOs(alunos.getContent());
        CollectionResponse<AlunoDTO,Aluno> alunosReponseDTO = new CollectionResponse<AlunoDTO,Aluno>(alunos, alunoDTOS);
        return alunosReponseDTO;
    }

    private Page<Aluno> recuperarListaDeAlunosDTODoBanco(Pageable pageable){
        Page<Aluno> alunos = Page.empty();
        try {
            alunos = iAlunoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return alunos;
    }

}
