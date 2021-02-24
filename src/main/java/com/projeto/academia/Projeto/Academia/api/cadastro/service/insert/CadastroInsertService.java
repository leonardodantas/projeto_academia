package com.projeto.academia.Projeto.Academia.api.cadastro.service.insert;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.select.CadastroSelectService;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.CalculadoraPagamento;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public CadastroDTO criarCadastro(CadastroDTO cadastroDTO) {
        alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(cadastroDTO.getIdAluno());
        cadastroDTO.setId(GeradorID.getInstance().gerarCodigo());
        this.defineValoresDoCadastro(cadastroDTO);
        Cadastro cadastro = cadastroAssembler.dtoParaEntidade(cadastroDTO);
        CadastroDTO cadastroSalvo = this.salvarNoBanco(cadastro);
        return cadastroSalvo;
    }

    private CadastroDTO salvarNoBanco(Cadastro cadastro){
        CadastroDTO cadastroDTO = new CadastroDTO();
        try {
            Cadastro cadastroSalvo = iCadastroRepository.save(cadastro);
            cadastroDTO = cadastroAssembler.entidadeParaDTO(cadastroSalvo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return cadastroDTO;
    }

    private void defineValoresDoCadastro(CadastroDTO cadastroDTO){
        calculadoraPagamento.calcularValorPagamento(cadastroDTO);
        calculadoraPagamento.calcularPorcentagem(cadastroDTO);
    }

    public CadastroDTO atualizarPorcentagemCadastro(String idCadastro, double novaPorcentagem) {
        CadastroDTO cadastroDTO = cadastroSelectService.retornarUsuarioOuLancaExcecao(idCadastro);
        if (novaPorcentagem > 0 && novaPorcentagem < 100) {
            cadastroDTO.setPorcentagemDesconto(novaPorcentagem);
        }
        this.defineValoresDoCadastro(cadastroDTO);
        Cadastro cadastro = cadastroAssembler.dtoParaEntidade(cadastroDTO);
        CadastroDTO cadastroSalvo = this.salvarNoBanco(cadastro);
        return cadastroSalvo;
    }
}
