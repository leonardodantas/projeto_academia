package com.projeto.academia.Projeto.Academia.api.usuario.controller;


import com.projeto.academia.Projeto.Academia.api.usuario.model.dto.UsuarioDTO;
import com.projeto.academia.Projeto.Academia.api.usuario.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(value = "Usuario Controller")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    @ApiOperation(value = "Criar novo usuario")
    public ResponseEntity<?> criarNovoUsuario(@Valid @RequestBody UsuarioDTO usuario){
        UsuarioDTO usuarioDTO = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }
}
