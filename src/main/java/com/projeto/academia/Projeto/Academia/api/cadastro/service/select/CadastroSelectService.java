package com.projeto.academia.Projeto.Academia.api.cadastro.service.select;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.Cadastro;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.assembler.CadastroAssembler;
import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
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
public class CadastroSelectService {

    @Autowired
    private ICadastroRepository iCadastroRepository;

    @Autowired
    private CadastroAssembler cadastroAssembler;

    public CadastroDTO recuperarCadastroPeloID(String idCadastro){
        return this.recuperarCadastroPeloIDNoBanco(idCadastro);
    }

    public CadastroDTO retornarCadastroOuLancaExcecao(String idCadastro){
        CadastroDTO cadastroDTO = this.recuperarCadastroPeloID(idCadastro);
        if (Strings.isNullOrEmpty(cadastroDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro não existe na base de dados");
        }
        return cadastroDTO;
    }

    private CadastroDTO recuperarCadastroPeloIDNoBanco(String idCadastro) {
        CadastroDTO cadastroDTO = new CadastroDTO();
        try {
            Optional<Cadastro> cadastro = iCadastroRepository.findById(idCadastro);
            if (cadastro.isPresent()) {
                cadastroDTO = cadastroAssembler.entidadeParaDTO(cadastro.get());
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return cadastroDTO;
    }

    public CollectionResponse<CadastroDTO, Cadastro> recuperarTodos(Pageable pageable) {
        Page<Cadastro> cadastroPage = iCadastroRepository.findAll(pageable);
        List<CadastroDTO> cadastroDTOList = cadastroAssembler.muitasEntidadesParaMuitosDTOs(cadastroPage.getContent());
        return new CollectionResponse<>(cadastroPage, cadastroDTOList);
    }
}
