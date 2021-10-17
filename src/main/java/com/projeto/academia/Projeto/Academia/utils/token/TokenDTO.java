package com.projeto.academia.Projeto.Academia.utils.token;

import lombok.Getter;

@Getter
public class TokenDTO {

    private static final String BEARER = "Bearer";

    private final String token;
    private final String type;

    private TokenDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public static TokenDTO bearerOf(String token) {
        return new TokenDTO(token, BEARER);
    }

}
