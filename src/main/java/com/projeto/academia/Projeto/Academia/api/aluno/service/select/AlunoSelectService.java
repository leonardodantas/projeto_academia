package com.projeto.academia.Projeto.Academia.api.aluno.service.select;

import com.projeto.academia.Projeto.Academia.api.aluno.model.Aluno;
import com.projeto.academia.Projeto.Academia.api.aluno.model.assembler.AlunoAssembler;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.repository.IAlunoRepository;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.select.AvaliacaoSelectService;
import com.projeto.academia.Projeto.Academia.utils.cpf.ValidarCPF;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoSelectService {

    @Autowired
    private AlunoAssembler alunoAssembler;

    @Autowired
    private IAlunoRepository iAlunoRepository;

    @Autowired
    private AvaliacaoSelectService avaliacaoSelectService;

    public CollectionResponse<AlunoDTO,Aluno> recuperarTodos(Pageable pageable) {
        Page<Aluno> alunos = recuperarListaDeAlunosDTODoBanco(pageable);
        List<AlunoDTO> alunoDTOS = alunoAssembler.muitasEntidadesParaMuitosDTOs(alunos.getContent());
        return new CollectionResponse<AlunoDTO,Aluno>(alunos, alunoDTOS);
    }

    public AlunoDTO recuperaAlunoPorCPF(String cpf){
        String cpfFormatado = ValidarCPF.formatarCPF(cpf);
        Optional<Aluno> aluno = recuperaAlunoPorCPFNoBanco(cpfFormatado);
        return this.verificaEConverteAlunoRecuperado(aluno);
    }


    public AlunoDTO lancaExcecaoSeJaExistirCPFNaBaseDeDados(String cpf){
        String cpfFormatado = ValidarCPF.formatarCPF(cpf);
        Optional<Aluno> aluno = recuperaAlunoPorCPFNoBanco(cpfFormatado);
        if (aluno.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF não existe na base de dados");
        }
        return this.verificaEConverteAlunoRecuperado(aluno);
    }

    public AlunoDTO recuperarAlunoPorId(String id){
        Optional<Aluno> aluno = recuperarAlunoPorIDNoBanco(id);
        return this.verificaEConverteAlunoRecuperado(aluno);
    }

    public AlunoDTO lancaExcecaoSenaoExistirAlunoPorID(String id){
        Optional<Aluno> aluno = recuperarAlunoPorIDNoBanco(id);
        if (!aluno.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não existe na base de dados");
        }
        return this.verificaEConverteAlunoRecuperado(aluno);
    }

    public AlunoDTO recuperarAlunoETodasAsAvalicoes(String idAluno) {
        AlunoDTO alunoDTO = recuperarAlunoPorId(idAluno);
        if (!alunoDTO.getId().isEmpty()) {
            List<AvaliacaoDTO> avaliacaoDTOList = avaliacaoSelectService.recuperarListaDeAvaliacoesPeloIDAluno(idAluno);
            alunoDTO.setAvaliacoes(avaliacaoDTOList);
        }
        return alunoDTO;
    }

    private AlunoDTO verificaEConverteAlunoRecuperado(Optional<Aluno> aluno){
        AlunoDTO alunoDTO = new AlunoDTO();
        if(aluno.isPresent()) {
            alunoDTO = alunoAssembler.entidadeParaDTO(aluno.get());
        }
        return  alunoDTO;
    }

    private Optional<Aluno> recuperarAlunoPorIDNoBanco(String id){
        Optional<Aluno> aluno = Optional.empty();
        try {
            aluno = iAlunoRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return aluno;
    }

    private Optional<Aluno> recuperaAlunoPorCPFNoBanco(String cpf) {
        Optional<Aluno> aluno = Optional.empty();
        try {
            aluno = iAlunoRepository.findByCpf(cpf);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return aluno;
    }

    private Page<Aluno> recuperarListaDeAlunosDTODoBanco(Pageable pageable){
        Page<Aluno> alunos = Page.empty();
        try {
            alunos = iAlunoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return alunos;
    }

}
