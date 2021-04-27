package com.projeto.academia.Projeto.Academia.api.autenticacao.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    @ApiModelProperty(value = "Email para autenticação", name = "email", dataType = "String", example = "leonardodantas@email.com.br")
    private String email;
    @ApiModelProperty(value = "Senha para autenticação", name = "senha", dataType = "String", example = "123456")
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
