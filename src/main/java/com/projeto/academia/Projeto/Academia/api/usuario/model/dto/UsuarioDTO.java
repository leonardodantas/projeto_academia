package com.projeto.academia.Projeto.Academia.api.usuario.model.dto;

import com.projeto.academia.Projeto.Academia.utils.geradorID.GeradorID;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @ApiModelProperty(value = "ID do usuario", name = "id", dataType = "String", example = "456789754121dasd4", hidden = true)
    private String id;
    @NotNull(message = "{not.null}") @Length(min = 10, message = "{length.invalid}")
    @ApiModelProperty(value = "Nome do usuario", name = "nome", dataType = "String", example = "Leonardo Rodrigues Dantas")
    private String nome;
    @NotNull(message = "{not.null}") @Length(min = 6, message = "{length.invalid}")
    @ApiModelProperty(value = "Senha do usuario", name = "senha", dataType = "String", example = "123456")
    private String senha;
    @Email
    @NotNull(message = "{not.null}") @Length(min = 10, message = "{length.invalid}")
    @ApiModelProperty(value = "Email do usuario", name = "email", dataType = "String", example = "leonardodantas@email.com.br")
    private String email;
    @ApiModelProperty(value = "Perfil do Usuario", name = "perfilUsuario", dataType = "PerfilUsuario.class", example = "ADM")
    private PerfilUsuario perfilUsuario;

    public void gerarID() {
        this.id = GeradorID.getInstance().gerarCodigo();
    }
}
