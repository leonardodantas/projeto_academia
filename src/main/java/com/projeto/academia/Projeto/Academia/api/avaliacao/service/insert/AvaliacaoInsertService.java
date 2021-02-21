package com.projeto.academia.Projeto.Academia.api.avaliacao.service.insert;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.aluno.model.dto.AlunoDTO;
import com.projeto.academia.Projeto.Academia.api.aluno.service.select.AlunoSelectService;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.Avaliacao;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.assembler.AvaliacaoAssembler;
import com.projeto.academia.Projeto.Academia.api.avaliacao.model.dto.AvaliacaoDTO;
import com.projeto.academia.Projeto.Academia.api.avaliacao.repository.IAvaliacaoRepository;
import com.projeto.academia.Projeto.Academia.api.avaliacao.service.select.AvaliacaoSelectService;
import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class AvaliacaoInsertService {

    @Autowired
    private IAvaliacaoRepository iAvaliacaoRepository;

    @Autowired
    private AvaliacaoAssembler avaliacaoAssembler;

    @Autowired
    private AlunoSelectService alunoSelectService;

    @Autowired
    private AvaliacaoSelectService avaliacaoSelectService;

    public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO avaliacaoDTO) {

        avaliacaoDTO.setId(GeradorID.getInstance().gerarCodigo());

        if (Objects.isNull(avaliacaoDTO.getDataAvaliacao())) {
            avaliacaoDTO.setDataAvaliacao(new Date());
        }
        AvaliacaoDTO avaliacaoDTOSalva = this.preparararAvaliacaoParaSerSalvaOuAtualizada(avaliacaoDTO);
        return avaliacaoDTOSalva;
    }

    public AvaliacaoDTO atualizarAvaliacao(AvaliacaoDTO avaliacaoDTO){

        if (Strings.isNullOrEmpty(avaliacaoDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avaliação para atualização não possui ID");
        }

        AvaliacaoDTO avaliacaoExiste = this.avaliacaoSelectService.recuperarAvaliacaoPeloID(avaliacaoDTO.getId());

        if (Strings.isNullOrEmpty(avaliacaoExiste.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhuma avaliação para ser atualizada");
        }

        if (Objects.isNull(avaliacaoDTO.getDataAtualizacaoAvaliacao())){
            avaliacaoDTO.setDataAtualizacaoAvaliacao(new Date());
        }
        avaliacaoDTO.setIdAluno(avaliacaoExiste.getIdAluno());
        avaliacaoDTO.setDataAvaliacao(avaliacaoExiste.getDataAvaliacao());

        AvaliacaoDTO avaliacaoDTOSalva = this.preparararAvaliacaoParaSerSalvaOuAtualizada(avaliacaoDTO);
        return  avaliacaoDTOSalva;
    }

    private void verificarSeExisteAlunoComCPF(AvaliacaoDTO avaliacaoDTO){
        AlunoDTO alunoDTO = alunoSelectService.recuperarAlunoPorId(avaliacaoDTO.getIdAluno());
        if (Strings.isNullOrEmpty(alunoDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não existe na base de dados");
        }
        AlunoDTO alunoComCPF = alunoSelectService.recuperaAlunoPorCPF(alunoDTO.getCpf());
        if (Strings.isNullOrEmpty(alunoComCPF.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não existe na base de dados");
        }
        avaliacaoDTO.setIdAluno(alunoComCPF.getId());
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
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        double alturaAoQuadrado = avaliacaoDTO.getAltura() * avaliacaoDTO.getAltura();
        double imcCalculado = avaliacaoDTO.getPeso() / alturaAoQuadrado;
        avaliacaoDTO.setImc(Double.parseDouble(decimalFormat.format(imcCalculado).replace(",", ".")));
    }
}
