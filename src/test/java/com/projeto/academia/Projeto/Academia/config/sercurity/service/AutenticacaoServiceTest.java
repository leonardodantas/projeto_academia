package com.projeto.academia.Projeto.Academia.config.sercurity.service;

import com.projeto.academia.Projeto.Academia.api.usuario.model.Usuario;
import com.projeto.academia.Projeto.Academia.api.usuario.repository.IUsuarioRepository;
import com.projeto.academia.Projeto.Academia.config.security.service.AutenticacaoService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Mock
    private IUsuarioRepository iUsuarioRepository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init(){
        String email = "leonardo1317@yahoo.com.br";
        when(iUsuarioRepository.findByEmail(email)).thenReturn(Optional.of(Usuario.builder().build()));
    }

    @Test
    public void deveRecuperarUsuarioPeloEmail(){
        String email = "leonardo1317@yahoo.com.br";
        UserDetails usuario = autenticacaoService.loadUserByUsername(email);
        assertNotNull(usuario);
    }

    @Test
    public void deveLancarExcecaoAoNaoEncontrarUsuario(){
        exceptionRule.expect(UsernameNotFoundException.class);
        autenticacaoService.loadUserByUsername("dantas@email.com.br");
    }
}
