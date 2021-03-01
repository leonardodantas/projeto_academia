package com.projeto.academia.Projeto.Academia.api.cadastro.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.select.CadastroSelectService;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.model.FormaPagamento;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.service.FormaPagamentoService;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.CalculadoraPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroInsertService {

    @Autowired
    private AlunoSelectService alunoSelectService;

    @Autowired
    private ICadastroRepository iCadastroRepository;

    @Autowired
    private CadastroAssembler cadastroAssembler;

    @Autowired
    private CalculadoraPagamento calculadoraPagamento;

    @Autowired
    private CadastroSelectService cadastroSelectService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public CadastroDTO criarCadastro(CadastroDTO cadastroDTO) {
        alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(cadastroDTO.getIdAluno());
        cadastroDTO.gerarIdCadastroEIdFormaPagamento();
        cadastroDTO = this.defineValoresDoCadastro(cadastroDTO);
        Cadastro cadastro = cadastroAssembler.dtoParaEntidade(cadastroDTO);
        CadastroDTO cadastroSalvo = this.salvarNoBanco(cadastro);
        return cadastroSalvo;
    }

    private CadastroDTO salvarNoBanco(Cadastro cadastro){
        CadastroDTO cadastroDTO = new CadastroDTO();
        try {
            FormaPagamento formaPagamento = formaPagamentoService.salvarFormaPagamento(cadastro.getFormaPagamento());
            Cadastro cadastroSalvo = iCadastroRepository.save(cadastro);
            cadastroSalvo.setFormaPagamento(formaPagamento);
            cadastroDTO = cadastroAssembler.entidadeParaDTO(cadastroSalvo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return cadastroDTO;
    }

    private CadastroDTO defineValoresDoCadastro(CadastroDTO cadastroDTO){
        cadastroDTO.setValorSemDesconto(calculadoraPagamento.calcularValorPagamento(cadastroDTO.getTipoPlanoPagamento()));
        cadastroDTO.setValorComDesconto(calculadoraPagamento.calcularValorComDesconto(cadastroDTO));
        return  cadastroDTO;
    }

    public CadastroDTO atualizarPorcentagemCadastro(String idCadastro, double novaPorcentagem) {
        CadastroDTO cadastroDTO = cadastroSelectService.retornarCadastroOuLancaExcecao(idCadastro);
        if (novaPorcentagem > 0 && novaPorcentagem < 100) {
            cadastroDTO.setPorcentagemDesconto(novaPorcentagem);
        }
        this.defineValoresDoCadastro(cadastroDTO);
        Cadastro cadastro = cadastroAssembler.dtoParaEntidade(cadastroDTO);
        CadastroDTO cadastroSalvo = this.salvarNoBanco(cadastro);
        return cadastroSalvo;
    }
}
