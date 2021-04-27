package com.projeto.academia.Projeto.Academia.api.aluno.service.insert;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.utils.cpf.ValidarCPF;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoInsertService {

    @Autowired
    private IAlunoRepository iAlunoRepository;

    @Autowired
    private AlunoAssembler alunoAssembler;

    @Autowired
    private AlunoSelectService alunoSelectService;

    public AlunoDTO inserirAluno(AlunoDTO alunoDTO) {
        alunoSelectService.lancaExcecaoSeJaExistirCPFNaBaseDeDados(alunoDTO.getCpf());
        alunoDTO.setId(GeradorID.gerarCodigo());
        Aluno aluno = alunoAssembler.dtoParaEntidade(alunoDTO);
        return this.inserirAlunoNoBanco(aluno);
    }

    private AlunoDTO inserirAlunoNoBanco(Aluno aluno) {
        AlunoDTO alunoDTOSalvo;
        aluno.setCpf(new ValidarCPF().formatarCPF(aluno.getCpf()));
        try {
            Aluno alunoSalvo = iAlunoRepository.save(aluno);
            alunoDTOSalvo = alunoAssembler.entidadeParaDTO(alunoSalvo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return alunoDTOSalvo;
    }

}
