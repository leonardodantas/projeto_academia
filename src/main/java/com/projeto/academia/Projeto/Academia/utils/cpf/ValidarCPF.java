package com.projeto.academia.Projeto.Academia.utils.cpf;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarCPF {

    public String formatarCPF(@NonNull String cpf){
        String cpfFormatado = "";

        if (cpf.length() != 11) {
            cpf = recuperarApenasNumeros(cpf);
        }

        cpfFormatado = gerarCPFComFormatacao(cpf);
        validarCPF(cpfFormatado);
        return cpfFormatado;
    }

    private String gerarCPFComFormatacao(String cpf){
        if (cpf.length() != 11) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "CPF com tamanho fora do padrão");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cpf.substring(0,3)).append(".");
        stringBuilder.append(cpf.substring(3,6)).append(".");
        stringBuilder.append(cpf.substring(6,9)).append("-");
        stringBuilder.append(cpf.substring(9,11));
        return  stringBuilder.toString();
    }
    
    private void validarCPF(String cpf) {
        Pattern padrao = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}");
        Matcher matcher = padrao.matcher(cpf);
        if (!matcher.matches() || cpf.length() != 14) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "CPF com formato errado");
        }
    }

    private String recuperarApenasNumeros(String cpf) {
        return cpf.replaceAll("[\\D+]","");
    }

}
