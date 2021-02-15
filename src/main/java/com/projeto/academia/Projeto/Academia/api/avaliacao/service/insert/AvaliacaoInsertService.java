package com.projeto.academia.Projeto.Academia.api.avaliacao.service.insert;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class AvaliacaoInsertService {

    @Autowired
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Autowired
    private AvaliacaoAssembler avaliacaoAssembler;

    @Autowired
    private AlunoSelectService alunoSelectService;

    public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO avaliacaoDTO) {

        avaliacaoDTO.setId(GeradorID.getInstance().gerarCodigo());

        if (Strings.isNullOrEmpty(avaliacaoDTO.getDataAvaliacao().toString())) {
            avaliacaoDTO.setDataAvaliacao(new Date());
        }
        AvaliacaoDTO avaliacaoDTOSalva = this.preparararAvaliacaoParaSerSalvaOuAtualizada(avaliacaoDTO);
        return avaliacaoDTOSalva;
    }

    public AvaliacaoDTO atualizarAvaliacao(AvaliacaoDTO avaliacaoDTO){

        if (avaliacaoDTO.getId().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avaliação para atualização não possui ID");
        }

        if (Strings.isNullOrEmpty(avaliacaoDTO.getDataAtualizacaoAvaliacao().toString())){
            avaliacaoDTO.setDataAtualizacaoAvaliacao(new Date());
        }

        AvaliacaoDTO avaliacaoDTOSalva = this.preparararAvaliacaoParaSerSalvaOuAtualizada(avaliacaoDTO);
        return  avaliacaoDTOSalva;
    }

    private void verificarSeExisteAlunoComCPF(AvaliacaoDTO avaliacaoDTO){
        AlunoDTO alunoDTO = alunoSelectService.recuperarAlunoPorId(avaliacaoDTO.getIdAluno());
        if (Strings.isNullOrEmpty(alunoDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não existe na base de dados");
        }
    }

    private AvaliacaoDTO preparararAvaliacaoParaSerSalvaOuAtualizada(AvaliacaoDTO avaliacaoDTO){
        this.verificarSeExisteAlunoComCPF(avaliacaoDTO);
        this.calcularIMC(avaliacaoDTO);
        Avaliacao avaliacao = avaliacaoAssembler.dtoParaEntidade(avaliacaoDTO);
        AvaliacaoDTO avaliacaoDTOSalva = this.salvarAvaliacaoNoBanco(avaliacao);

        return avaliacaoDTOSalva;
    }

    private AvaliacaoDTO salvarAvaliacaoNoBanco(Avaliacao avaliacao){
        AvaliacaoDTO avaliacaoDTO;
        try {
             Avaliacao avaliacaoSalva = iAvaliacaoRepository.save(avaliacao);
             avaliacaoDTO = avaliacaoAssembler.entidadeParaDTO(avaliacaoSalva);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return avaliacaoDTO;
    }

    private void calcularIMC(AvaliacaoDTO avaliacaoDTO) {
        double alturaAoQuadrado = avaliacaoDTO.getAltura() * avaliacaoDTO.getAltura();
        avaliacaoDTO.setImc(avaliacaoDTO.getPeso() * alturaAoQuadrado);
    }
}
