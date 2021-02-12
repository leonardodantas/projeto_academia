package com.projeto.academia.Projeto.Academia.api.aluno.service.insert;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.utils.cpf.ValidarCPF;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoInsertService {

    @Autowired
    private IAlunoRepository iAlunoRepository;

    @Autowired
    private AlunoAssembler alunoAssembler;

    @Autowired
    private AlunoSelectService alunoSelectService;

    public AlunoDTO inserirAluno(AlunoDTO alunoDTO) {

        alunoDTO.setCpf(new ValidarCPF().formatarCPF(alunoDTO.getCpf()));
        AlunoDTO alunoComCPFRepetido = alunoSelectService.recuperaAlunoPorCPF(alunoDTO.getCpf());

        if (!Strings.isNullOrEmpty(alunoComCPFRepetido.getId())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "CPF já existe na base de dados");
        }

        alunoDTO.setId(GeradorID.getInstance().gerarCodigo());
        Aluno aluno = alunoAssembler.dtoParaEntidade(alunoDTO);
        AlunoDTO alunoSalvo = this.inserirAlunoNoBanco(aluno);
        return alunoSalvo;
    }

    private AlunoDTO inserirAlunoNoBanco(Aluno aluno) {
        AlunoDTO alunoDTOSalvo;
        try {
            Aluno alunoSalvo = iAlunoRepository.save(aluno);
            alunoDTOSalvo = alunoAssembler.entidadeParaDTO(alunoSalvo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return alunoDTOSalvo;
    }

}
