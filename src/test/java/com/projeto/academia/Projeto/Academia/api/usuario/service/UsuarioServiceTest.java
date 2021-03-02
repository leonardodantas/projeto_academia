package com.projeto.academia.Projeto.Academia.api.usuario.service;

import com.projeto.academia.Projeto.Academia.api.usuario.model.Usuario;
import com.projeto.academia.Projeto.Academia.api.usuario.model.assembler.UsuarioAssembler;
import com.projeto.academia.Projeto.Academia.api.usuario.model.dto.UsuarioDTO;
import com.projeto.academia.Projeto.Academia.api.usuario.repository.IUsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private IUsuarioRepository iUsuarioRepository;

    @Mock
    private UsuarioAssembler usuarioAssembler;

    @Test
    public void deveSalvarUsuario(){
        UsuarioDTO dto = UsuarioDTO.builder()
                .id("12345678912345678912")
                .email("leonardodantas@dantas.com.br")
                .nome("Leonardo Rodrigues Dantas")
                .senha("123456")
                .build();

        Usuario usuario = Usuario.builder()
                .id("12345678912345678912")
                .email("leonardodantas@dantas.com.br")
                .nome("Leonardo Rodrigues Dantas")
                .senha("123456")
                .build();

        when(usuarioAssembler.dtoParaEntidade(dto)).thenReturn(usuario);
        when(usuarioAssembler.entidadeParaDTO(usuario)).thenReturn(dto);
        when(iUsuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDTO usuarioDTO = usuarioService.salvarUsuario(dto);
        assertNotNull(usuarioDTO);
        assertEquals(usuarioDTO.getId().length(), 20);
    }
}
