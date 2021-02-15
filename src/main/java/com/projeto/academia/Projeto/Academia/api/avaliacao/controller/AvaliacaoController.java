package com.projeto.academia.Projeto.Academia.api.avaliacao.controller;

import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.delete.AvalicaoDeleteService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.insert.AvaliacaoInsertService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.select.AvaliacaoSelectService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(value = "Avaliações Controller")
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoInsertService avaliacaoInsertService;

    @Autowired
    private AvalicaoDeleteService avalicaoDeleteService;

    @Autowired
    private AvaliacaoSelectService avaliacaoSelectService;

    @PostMapping
    @ApiOperation(value = "Criação de uma nova avaliação")
    public ResponseEntity<?> criarAvaliacao(AvaliacaoDTO avaliacaoDTO){
        AvaliacaoDTO dto = avaliacaoInsertService.criarAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @ApiOperation(value = "Atualização de uma avaliação")
    public ResponseEntity<?> atualizarAvaliacao(AvaliacaoDTO avaliacaoDTO){
        AvaliacaoDTO dto = avaliacaoInsertService.atualizarAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{idAvaliacao}")
    public ResponseEntity<?> removerAvaliacao(@PathVariable String idAvaliacao){
        AvaliacaoDTO dto = avalicaoDeleteService.removerAvaliacao(idAvaliacao);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/aluno/{idAluno}")
    public ResponseEntity<?> removerTodasAvalicoesDoUsuario(@PathVariable String idAluno){
        AlunoDTO dto = avalicaoDeleteService.removerTodasAsAvaliacoesDoUsuario(idAluno);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> recuperarTodos(@PageableDefault(page = 0,size = 20) Pageable pageable){
        CollectionResponse<AvaliacaoDTO, Avaliacao> response = avaliacaoSelectService.recuperarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<?> recuperarTodasAvalicoesDeAluno(@PageableDefault(page = 0,size = 20) Pageable pageable, @PathVariable String idAluno){
        CollectionResponse<AvaliacaoDTO, Avaliacao> response = avaliacaoSelectService.recuperarTodasAvaliacoesDeAluno(idAluno,pageable);
        return ResponseEntity.ok(response);
    }
}
