package com.projeto.academia.Projeto.Academia.api.models.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class LoginDTO {

    @ApiModelProperty(value = "Email para autenticação", name = "email", dataType = "String", example = "leonardodantas@email.com.br")
    private String email;
    @ApiModelProperty(value = "Senha para autenticação", name = "senha", dataType = "String", example = "123456")
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
