package com.projeto.academia.Projeto.Academia.api.models.dto;

import com.projeto.academia.Projeto.Academia.api.models.entitys.User;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class UserDTO implements DataTransferObject {

    @ApiModelProperty(value = "ID do usuario", name = "id", dataType = "String", example = "456789754121dasd4", hidden = true)
    private String id;
    @NotNull(message = "{not.null}") @Length(min = 10, message = "{length.invalid}")
    @ApiModelProperty(value = "Nome do usuario", name = "nome", dataType = "String", example = "Leonardo Rodrigues Dantas")
    private String name;
    @NotNull(message = "{not.null}") @Length(min = 6, message = "{length.invalid}")
    @ApiModelProperty(value = "Senha do usuario", name = "senha", dataType = "String", example = "123456")
    private String password;
    @Email
    @NotNull(message = "{not.null}") @Length(min = 10, message = "{length.invalid}")
    @ApiModelProperty(value = "Email do usuario", name = "email", dataType = "String", example = "leonardodantas@email.com.br")
    private String email;
    @ApiModelProperty(value = "Perfil do Usuario", name = "perfilUsuario", dataType = "PerfilUsuario.class", example = "ADM")
    private ProfileUser profileUser;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public static UserDTO from(User user) {
        return new UserDTO(user);
    }

}
