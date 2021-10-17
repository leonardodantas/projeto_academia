package com.projeto.academia.Projeto.Academia.config.security.service;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.models.entitys.User;
import com.projeto.academia.Projeto.Academia.api.repositorys.user.IUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private final TokenService tokenService;
    private final IUserRepository repository;

    private AuthenticationTokenFilter(TokenService tokenService, IUserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    public static Filter of(TokenService tokenService, IUserRepository userRepository) {
        return new AuthenticationTokenFilter(tokenService, userRepository);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);
        boolean isValid = tokenService.isTokenValido(token);
        if (isValid) {
            authenticateUser(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        String idUser = tokenService.getIdUser(token);
        User user = repository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (Strings.isNullOrEmpty(token) || token.isEmpty()) {
            return null;
        }
        return token;
    }
}
