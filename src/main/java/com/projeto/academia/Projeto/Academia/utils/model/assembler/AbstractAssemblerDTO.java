package com.projeto.academia.Projeto.Academia.utils.model.assembler;

import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractAssemblerDTO<entidade extends Entity,dto extends DataTransferObject> {

    public List<dto> muitasEntidadesParaMuitosDTOs(List<entidade> entidades){
        return entidades.stream()
                .map(this::entidadeParaDTO)
                .collect(Collectors.toList());
    }

    public List<entidade> muitosDTOsParaMuitasEntidade(List<dto> dtos){
        return dtos.stream()
                .map(this::dtoParaEntidade)
                .collect(Collectors.toList());
    }

    public abstract dto entidadeParaDTO(entidade entidade);
    public abstract entidade dtoParaEntidade(dto dto);
}
