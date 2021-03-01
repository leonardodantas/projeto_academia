package com.projeto.academia.Projeto.Academia.api.cadastro.service.select;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Mock
    private Pageable pageable;

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
        when(iCadastroRepository.findById(idCadastro)).thenReturn(Optional.of(cadastro));

        Cadastro cadastroInexistente = Cadastro.builder().build();
        CadastroDTO cadastroDTOInexistete = CadastroDTO.builder().build();

        when(iCadastroRepository.findById(idCadastroInexistente)).thenReturn(Optional.of(cadastroInexistente));
        when(cadastroAssembler.entidadeParaDTO(cadastroInexistente)).thenReturn(cadastroDTOInexistete);

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

    @Test
    public void deveRecuperarTodos(){
        Page<Cadastro> cadastroPage = this.criarPage();
        List<CadastroDTO> cadastroDTO = this.criarListaCadastroDTO();
        when(iCadastroRepository.findAll(Matchers.isA(Pageable.class))).thenReturn(cadastroPage);
        when(cadastroAssembler.muitasEntidadesParaMuitosDTOs(cadastroPage.getContent())).thenReturn(cadastroDTO);
        CollectionResponse<CadastroDTO, Cadastro> response = cadastroSelectService.recuperarTodos(pageable);
        assertEquals(response.getSize(), 10);
        assertNotNull(response);
    }

    private List<CadastroDTO> criarListaCadastroDTO() {
        List<CadastroDTO> cadastroDTOList = new ArrayList<>();
        for(int i=0; i<10; i++){
            CadastroDTO cadastroDTO = CadastroDTO.builder()
                    .id(String.valueOf(i + 1))
                    .build();
            cadastroDTOList.add(cadastroDTO);
        }
        return cadastroDTOList;
    }

    private Page<Cadastro> criarPage() {
        List<Cadastro> cadastros = new ArrayList<>();
        for (int i=0; i< 10; i++){
            Cadastro cadastro = Cadastro.builder()
                    .id(String.valueOf(i + 1))
                    .build();
            cadastros.add(cadastro);
        }
        return new PageImpl<>(cadastros);
    }
}
