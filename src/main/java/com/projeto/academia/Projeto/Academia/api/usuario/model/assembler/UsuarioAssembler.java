package com.projeto.academia.Projeto.Academia.api.usuario.model.assembler;

import com.projeto.academia.Projeto.Academia.api.usuario.model.Perfil;
import com.projeto.academia.Projeto.Academia.api.usuario.model.Usuario;
import com.projeto.academia.Projeto.Academia.api.usuario.model.dto.UsuarioDTO;
import com.projeto.academia.Projeto.Academia.utils.model.assembler.AbstractAssemblerDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioAssembler extends AbstractAssemblerDTO<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO entidadeParaDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .build();
    }

    @Override
    public Usuario dtoParaEntidade(UsuarioDTO usuarioDTO) {

        List<Perfil> perfilList = getPerfils(usuarioDTO);

        return Usuario.builder()
                .id(usuarioDTO.getId())
                .email(usuarioDTO.getEmail())
                .nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha())
                .perfis(perfilList)
                .build();
    }

    private List<Perfil> getPerfils(UsuarioDTO usuarioDTO) {
        List<Perfil> perfilList = new ArrayList<>();
        Perfil perfil = Perfil.builder()
                .nome("ROLE_" + usuarioDTO.getPerfilUsuario().getPerfil())
                .build();

        perfilList.add(perfil);
        return perfilList;
    }
}
