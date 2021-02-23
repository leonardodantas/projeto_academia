package com.projeto.academia.Projeto.Academia.api.cadastro.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.api.formaPagamento.tipoPlanoPagamento.CalculadoraPagamento;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
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

    public CadastroDTO criarCadastro(CadastroDTO cadastroDTO) {
        alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(cadastroDTO.getIdAluno());
        cadastroDTO.setId(GeradorID.getInstance().gerarCodigo());

        double valor = calculadoraPagamento.calcularValorPagamento(cadastroDTO.getFormaPagamento().getTipoPlanoPagamento());
        System.out.println(valor);
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
}
