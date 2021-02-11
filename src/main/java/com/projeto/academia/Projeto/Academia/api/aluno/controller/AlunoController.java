package com.projeto.academia.Projeto.Academia.api.aluno.controller;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.insert.AlunoInsertService;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Api(value = "Aluno Controller")
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoInsertService alunoInsertService;

    @Autowired
    private AlunoSelectService alunoSelectService;

    @ApiOperation(value = "Cria um novo aluno")
    @PostMapping
    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO){
        AlunoDTO alunoSalvo = alunoInsertService.inserirAluno(alunoDTO);
        return ResponseEntity.ok(alunoSalvo);
    }

    @GetMapping
    public ResponseEntity<?> recuperarTodos(@PageableDefault(page = 0, size = 20) Pageable pageable){
        List<Aluno> alunos = alunoSelectService.teste(pageable);
        return ResponseEntity.ok(alunos);
    }
}
