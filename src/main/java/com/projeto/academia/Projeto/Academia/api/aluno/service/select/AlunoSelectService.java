package com.projeto.academia.Projeto.Academia.api.aluno.service.select;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoSelectService {

    @Autowired
    private AlunoAssembler alunoAssembler;

    @Autowired
    private IAlunoRepository iAlunoRepository;

    public List<Aluno> teste(Pageable pageable){

        Page<Aluno> alunos = iAlunoRepository.findAll(pageable);
        List<Aluno> alunoss = alunos.get().collect(Collectors.toList());
        return alunoss;
    }
}
