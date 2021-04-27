package com.projeto.academia.Projeto.Academia.utils.model.assembler;

import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractAssemblerDTO<entidade extends Entity,dto extends DataTransferObject> {

    public List<dto> muitasEntidadesParaMuitosDTOs(List<entidade> entidades){
        List<dto> dtos = new ArrayList<>();
        for (entidade entidad : entidades) {
            dto dataTransferObject = entidadeParaDTO(entidad);
            dtos.add(dataTransferObject);
        }
        return dtos;
    }

    public List<entidade> muitosDTOsParaMuitasEntidade(List<dto> dtos){
        List<entidade> entidades = new ArrayList<>();
        for (dto dt : dtos) {
            entidade entidade = dtoParaEntidade(dt);
            entidades.add(entidade);
        }
        return entidades;
    }

    public abstract dto entidadeParaDTO(entidade entidade);
    public abstract entidade dtoParaEntidade(dto dto);
}
