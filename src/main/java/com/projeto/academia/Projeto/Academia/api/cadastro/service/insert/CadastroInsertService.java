package com.projeto.academia.Projeto.Academia.api.cadastro.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroInsertService {

    @Autowired
    private AlunoSelectService alunoSelectService;

    @Autowired
    private ICadastroRepository iCadastroRepository;

    public CadastroDTO criarCadastro(CadastroDTO cadastroDTO) {
        alunoSelectService.lancaExcecaoSenaoExistirAlunoPorID(cadastroDTO.getIdAluno());
        cadastroDTO.setId(GeradorID.getInstance().gerarCodigo());

        
        return null;
    }

    private Cadastro salvarNoBanco(Cadastro cadastro){
        Cadastro cadastroSalvo = new Cadastro();
        try {
            cadastroSalvo = iCadastroRepository.save(cadastro);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return cadastroSalvo;
    }
}
