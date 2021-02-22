package com.projeto.academia.Projeto.Academia.api.cadastro.controller;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.insert.CadastroInsertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(value = "Cadastro Controller")
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroInsertService cadastroInsertService;

    @PostMapping
    @ApiOperation(value = "Criação de um novo cadastro")
    public ResponseEntity<?> criarCadastro(@Valid @RequestBody CadastroDTO cadastroDTO){
        CadastroDTO cadastroCriado = cadastroInsertService.criarCadastro(cadastroDTO);
        return ResponseEntity.ok(cadastroCriado);
    }
}
