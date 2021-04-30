package com.projeto.academia.Projeto.Academia.api.cadastro.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.select.CadastroSelectService;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.dto.FormaPagamentoDTO;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.service.FormaPagamentoService;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.CalculadoraPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.TipoPlanoPagamento;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CadastroInsertServiceTest {

    @InjectMocks
    private CadastroInsertService cadastroInsertService;

    @Mock
    private CadastroAssembler cadastroAssembler;

    @Mock
    private CalculadoraPagamento calculadoraPagamento;

    @Mock
    private ICadastroRepository iCadastroRepository;

    @Mock
    private CadastroSelectService cadastroSelectService;

    @Mock
    private AlunoSelectService alunoSelectService;

    @Mock
    private FormaPagamentoService formaPagamentoService;

    private CadastroDTO cadastroDTO;

    private Cadastro cadastro;

    private FormaPagamentoDTO formaPagamentoDTO;

    private FormaPagamento formaPagamento;

    @Before
    public void init(){

        String idAluno = "123";
        this.formaPagamentoDTO = FormaPagamentoDTO.builder()
                .tipoPlanoPagamento(TipoPlanoPagamento.MENSAL)
                .porcentagemDesconto(0)
                .build();
        this.formaPagamento = FormaPagamento.builder()
                .tipoPlanoPagamento(TipoPlanoPagamento.MENSAL)
                .porcentagemDesconto(0)
                .build();
        this.cadastroDTO = CadastroDTO.builder()
                .idAluno(idAluno)
                .formaPagamento(formaPagamentoDTO)
                .build();
        this.cadastro = Cadastro.builder()
                .idAluno(idAluno)
                .formaPagamento(formaPagamento)
                .build();

        when(cadastroAssembler.dtoParaEntidade(cadastroDTO)).thenReturn(cadastro);
        when(cadastroAssembler.entidadeParaDTO(cadastro)).thenReturn(cadastroDTO);
        when(iCadastroRepository.save(cadastro)).thenReturn(cadastro);

        when(calculadoraPagamento.calcularValorPagamento(cadastroDTO.getTipoPlanoPagamento())).thenReturn(75.0);
        when(calculadoraPagamento.calcularValorComDesconto(cadastroDTO)).thenReturn(75.0);

        String idCadastro = "456";
        when(cadastroSelectService.retornarCadastroOuLancaExcecao(idCadastro)).thenReturn(cadastroDTO);

    }

    @Test
    public void deveInserirCadastro(){
        CadastroDTO cadastroInserido = cadastroInsertService.criarCadastro(cadastroDTO);
        assertEquals(cadastroInserido.getId(), cadastroDTO.getId());
        assertEquals(cadastroInserido.getIdAluno(), cadastroDTO.getIdAluno());
        assertEquals(cadastroInserido.getTipoPlanoPagamento(), TipoPlanoPagamento.MENSAL);
        assertThat(cadastroInserido.getId()).hasSize(20);
    }

    @Test
    public void deveInserirNovaPorcentagem(){
        String idCadastro = "456";
        double novaPorcentagem = 15.0;
        CadastroDTO cadastroInserido = cadastroInsertService.atualizarPorcentagemCadastro(idCadastro, novaPorcentagem);
        assertThat(cadastroInserido.getPorcentagemDesconto()).isEqualTo(novaPorcentagem);
    }

    @Test
    public void naoDeveInserirNovaPorcentagem(){
        String idCadastro = "456";
        double novaPorcentagem = 150.0;
        CadastroDTO cadastroInserido = cadastroInsertService.atualizarPorcentagemCadastro(idCadastro, novaPorcentagem);
        assertThat(cadastroInserido.getPorcentagemDesconto()).isEqualTo(0.0);
    }

}
