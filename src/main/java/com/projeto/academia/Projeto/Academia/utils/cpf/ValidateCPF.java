package com.projeto.academia.Projeto.Academia.utils.cpf;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCPF {

    private static final String CPF_LENGTH = "CPF com tamanho fora do padrão";
    private static final String CPF_ERROR_FORMAT = "CPF com formato errado";

    public static String format(@NonNull String cpf){
        String cpfFormat = "";

        if (cpf.length() != 11) {
            cpf = getOnlyNumbers(cpf);
        }

        cpfFormat = generateCPFWithFormatting(cpf);
        validateCPF(cpfFormat);
        return cpfFormat;
    }

    private static String generateCPFWithFormatting(String cpf){
        if (cpf.length() != 11) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, CPF_LENGTH);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cpf, 0, 3).append(".");
        stringBuilder.append(cpf, 3, 6).append(".");
        stringBuilder.append(cpf, 6, 9).append("-");
        stringBuilder.append(cpf, 9, 11);
        return  stringBuilder.toString();
    }
    
    private static void validateCPF(String cpf) {
        Pattern pattern = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}");
        Matcher matcher = pattern.matcher(cpf);
        if (!matcher.matches() || cpf.length() != 14) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, CPF_ERROR_FORMAT);
        }
    }

    private static String getOnlyNumbers(String cpf) {
        return cpf.replaceAll("[\\D+]","");
    }

}
