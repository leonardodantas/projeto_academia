package com.projeto.academia.Projeto.Academia.config.security;

import com.projeto.academia.Projeto.Academia.api.usuario.repository.IUsuarioRepository;
import com.projeto.academia.Projeto.Academia.config.security.service.AutenticacaoService;
import com.projeto.academia.Projeto.Academia.config.security.service.AutenticacaoViaTokenFilter;
import com.projeto.academia.Projeto.Academia.config.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario/create").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/aluno").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.POST, "/aluno").hasRole("ADM")
                .antMatchers(HttpMethod.GET, "/aluno/*").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.GET, "/avaliacoes").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.POST, "/avaliacoes").hasRole("PERSONAL")
                .antMatchers(HttpMethod.PUT, "/avaliacoes").hasRole("PERSONAL")
                .antMatchers(HttpMethod.GET, "/avaliacoes/*").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.DELETE, "/avaliacoes/*").hasRole("PERSONAL")
                .antMatchers(HttpMethod.DELETE, "/avaliacoes/aluno/*").hasRole("PERSONAL")
                .antMatchers(HttpMethod.GET, "/avaliacoes/ultimaAvaliacao/*").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.GET, "/avaliacoes/ultimaAvaliacao/atualizada/*").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.GET, "/cadastro").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.GET, "/cadastro/*").hasAnyRole("ADM","PERSONAL")
                .antMatchers(HttpMethod.POST, "/cadastro").hasRole("ADM")
                .antMatchers(HttpMethod.PUT, "/cadastro").hasRole("ADM")
                .antMatchers(HttpMethod.DELETE, "/cadastro/*").hasRole("ADM")
                .anyRequest().denyAll()
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/h2");
    }
}
