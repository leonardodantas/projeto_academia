package com.projeto.academia.Projeto.Academia.api.cadastro.controller;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.delete.CadastroDeleteService;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.insert.CadastroInsertService;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.select.CadastroSelectService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(value = "Cadastro Controller")
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroInsertService cadastroInsertService;

    @Autowired
    private CadastroSelectService cadastroSelectService;

    @Autowired
    private CadastroDeleteService cadastroDeleteService;

    @PostMapping
    @ApiOperation(value = "Criação de um novo cadastro")
    public ResponseEntity<?> criarCadastro(@Valid @RequestBody CadastroDTO cadastroDTO){
        CadastroDTO cadastroCriado = cadastroInsertService.criarCadastro(cadastroDTO);
        return ResponseEntity.ok(cadastroCriado);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar a porcentagem de um cadastro")
    public ResponseEntity<?> atualizarPorcentagemCadastro(@RequestParam(required = true, name = "cadastro") String idCadastro,
                                                          @RequestParam(required = true, name = "porcentagem") double novaPorcentagem){
        CadastroDTO cadastroDTO = cadastroInsertService.atualizarPorcentagemCadastro(idCadastro, novaPorcentagem);
        return ResponseEntity.ok(cadastroDTO);
    }

    @GetMapping
    @ApiOperation(value = "Recuperar todos os cadastros")
    public ResponseEntity<?> recuperarTodosOsCadastros(@PageableDefault(page = 0, size = 20) Pageable pageable){
        CollectionResponse<CadastroDTO, Cadastro> response = cadastroSelectService.recuperarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Recuperar cadastro pelo ID")
    public ResponseEntity<?> recuperarCadastroPeloID(@PathVariable("id") String id){
        CadastroDTO cadastroDTO = cadastroSelectService.recuperarCadastroPeloID(id);
        if (Strings.isNullOrEmpty(cadastroDTO.getId())){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cadastroDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerCadastroPeloID(@PathVariable("id") String id){
        CadastroDTO cadastroDTO = cadastroDeleteService.deletarCadastroPeloID(id);
        return ResponseEntity.ok(cadastroDTO);
    }
}
