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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> criarAvaliacao(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO){
        AvaliacaoDTO dto = avaliacaoInsertService.criarAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @ApiOperation(value = "Atualização de uma avaliação")
    public ResponseEntity<?> atualizarAvaliacao(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO){
        AvaliacaoDTO dto = avaliacaoInsertService.atualizarAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{idAvaliacao}")
    @Transactional
    @ApiOperation(value = "Remove uma avaliação a partir do ID da avaliação")
    public ResponseEntity<?> removerAvaliacao(@PathVariable String idAvaliacao){
        AvaliacaoDTO dto = avalicaoDeleteService.removerAvaliacao(idAvaliacao);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/aluno/{idAluno}")
    @Transactional
    @ApiOperation(value = "Remove todas as avaliações de uma aluno a partir do ID do aluno")
    public ResponseEntity<?> removerTodasAvalicoesDoAluno(@PathVariable String idAluno){
        AlunoDTO dto = avalicaoDeleteService.removerTodasAsAvaliacoesDoAluno(idAluno);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @ApiOperation(value = "Recupera todas as avaliações")
    public ResponseEntity<?> recuperarTodos(@PageableDefault(page = 0,size = 20) Pageable pageable){
        CollectionResponse<AvaliacaoDTO, Avaliacao> response = avaliacaoSelectService.recuperarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idAluno}")
    @ApiOperation(value = "Recupera todas as avaliações de determinado aluno pelo seu ID")
    public ResponseEntity<?> recuperarTodasAvalicoesDeAluno(@PageableDefault(page = 0,size = 20) Pageable pageable, @PathVariable String idAluno){
        CollectionResponse<AvaliacaoDTO, Avaliacao> response = avaliacaoSelectService.recuperarTodasAvaliacoesDeAluno(idAluno,pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ultimaAvaliacao/{idAluno}")
    @ApiOperation(value = "Recupera ultima avaliação do aluno de acordo com o atributo Data de Avaliação")
    public ResponseEntity<?> recuperarUltimaAvaliacaoDeAlunoPelaData(@PathVariable String idAluno){
        AvaliacaoDTO avaliacaoDTO = avaliacaoSelectService.recuperarUltimaAvaliacaoDoAluno(idAluno);
        return ResponseEntity.ok(avaliacaoDTO);
    }

    @GetMapping("/ultimaAvaliacao/atualizada/{idAluno}")
    @ApiOperation(value = "Recupera ultima avaliação do aluno de acordo com o atributo Data de atualização da Avaliação")
    public ResponseEntity<?> recuperarUltimaAvaliacaoDeAlunoPelaDataAtualizada(@PathVariable String idAluno){
        AvaliacaoDTO avaliacaoDTO = avaliacaoSelectService.recuperarUltimaAvaliacaoDoAlunoAtualizada(idAluno);
        return ResponseEntity.ok(avaliacaoDTO);
    }
}
