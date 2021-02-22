package com.projeto.academia.Projeto.Academia.api.cadastro.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroInsertService {

    @Autowired
    private AlunoSelectService alunoSelectService;

    public CadastroDTO criarCadastro(CadastroDTO cadastroDTO) {
        alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(cadastroDTO.getIdAluno());
        cadastroDTO.getFormaPagamento().getTipoPlanoPagamento();
        return null;
    }
}
