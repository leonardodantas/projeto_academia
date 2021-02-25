package com.projeto.academia.Projeto.Academia.api.cadastro.service.select;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CadastroSelectServiceTest {

    @InjectMocks
    private CadastroSelectService cadastroSelectService;

    @Mock
    private ICadastroRepository iCadastroRepository;

    @Mock
    private CadastroAssembler cadastroAssembler;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private Cadastro cadastro;

    private CadastroDTO cadastroDTO;

    @Before
    public void init(){
        String idCadastro = "123";
        String idCadastroInexistente = "456";

        this.cadastroDTO = CadastroDTO.builder()
                .id(idCadastro)
                .build();
        this.cadastro = Cadastro.builder()
                .id(idCadastro)
                .build();
        when(cadastroAssembler.entidadeParaDTO(cadastro)).thenReturn(cadastroDTO);
        when(cadastroAssembler.dtoParaEntidade(cadastroDTO)).thenReturn(cadastro);
        when(iCadastroRepository.findById(idCadastro)).thenReturn(Optional.of(cadastro));

        Cadastro cadastroInexistente = Cadastro.builder().build();
        CadastroDTO cadastroDTOInexistete = CadastroDTO.builder().build();

        when(iCadastroRepository.findById(idCadastroInexistente)).thenReturn(Optional.of(cadastroInexistente));
        when(cadastroAssembler.entidadeParaDTO(cadastroInexistente)).thenReturn(cadastroDTOInexistete);
        when(cadastroAssembler.dtoParaEntidade(cadastroDTOInexistete)).thenReturn(cadastroInexistente);

    }

    @Test
    public void deveRecuperarCadastro(){
        String idCadastro = "123";
        CadastroDTO cadastroRecuperado = cadastroSelectService.recuperarCadastroPeloID(idCadastro);
        assertEquals(cadastro.getId(), cadastroRecuperado.getId());
    }

    @Test
    public void deveRetornarCadastro(){
        String idCadastro = "123";
        CadastroDTO cadastroRecuperado = cadastroSelectService.retornarCadastroOuLancaExcecao(idCadastro);
        assertEquals(cadastro.getId(), cadastroRecuperado.getId());
    }

    @Test
    public void deveLancarExececaoAoRecuperarCadastroInexistente(){
        String idCadastro = "456";
        exceptionRule.expect(ResponseStatusException.class);
        cadastroSelectService.retornarCadastroOuLancaExcecao(idCadastro);
    }

}
