package com.projeto.academia.Projeto.Academia.api.aluno.controller;

import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.insert.AlunoInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoInsertService alunoInsertService;

    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO){
        AlunoDTO alunoSalvo = alunoInsertService.inserirAluno(alunoDTO);
        return ResponseEntity.ok(alunoSalvo);
    }
}
