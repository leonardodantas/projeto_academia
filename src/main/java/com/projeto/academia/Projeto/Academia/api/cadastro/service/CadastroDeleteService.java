package com.projeto.academia.Projeto.Academia.api.cadastro.service;

import com.projeto.academia.Projeto.Academia.api.cadastro.model.dto.CadastroDTO;
import com.projeto.academia.Projeto.Academia.api.cadastro.repository.ICadastroRepository;
import com.projeto.academia.Projeto.Academia.api.cadastro.service.select.CadastroSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroDeleteService {

    @Autowired
    private ICadastroRepository iCadastroRepository;

    @Autowired
    private CadastroSelectService cadastroSelectService;

    public CadastroDTO deletarCadastroPeloID(String id){
        CadastroDTO cadastroDTO = cadastroSelectService.retornarUsuarioOuLancaExcecao(id);
        this.deletarCadastroNaBaseDeDados(id);
        return cadastroDTO;
    }

    private void deletarCadastroNaBaseDeDados(String id){
        try {
            iCadastroRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
