package com.projeto.academia.Projeto.Academia.api.usuario.service;

import com.projeto.academia.Projeto.Academia.api.usuario.model.Usuario;
import com.projeto.academia.Projeto.Academia.api.usuario.model.assembler.UsuarioAssembler;
import com.projeto.academia.Projeto.Academia.api.usuario.model.dto.UsuarioDTO;
import com.projeto.academia.Projeto.Academia.api.usuario.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private UsuarioAssembler usuarioAssembler;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        usuarioDTO.gerarID();
        Usuario usuario = usuarioAssembler.dtoParaEntidade(usuarioDTO);
        UsuarioDTO usuarioDTOSalvo = this.salvarUsuarioNoBanco(usuario);
        return usuarioDTOSalvo;
    }

    private UsuarioDTO salvarUsuarioNoBanco(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            Usuario usuarioSalvo = iUsuarioRepository.save(usuario);
            usuarioDTO = usuarioAssembler.entidadeParaDTO(usuarioSalvo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return usuarioDTO;
    }
}
