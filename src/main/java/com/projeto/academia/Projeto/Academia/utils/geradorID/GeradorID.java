package com.projeto.academia.Projeto.Academia.utils.geradorID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class GeradorID {

    private static GeradorID geradorID;
    private static List<String> codigosGerados;

    private GeradorID(){
        this.codigosGerados = new ArrayList<>();
    }

    private static GeradorID getInstance(){
        if (Objects.isNull(geradorID)){
            return new GeradorID();
        }
        return geradorID;
    }

    public static String gerarCodigo(){
        getInstance();

        String uuidRandom = gerarUUID();
        String codigo = validacaoUUID(uuidRandom);
        codigosGerados.add(codigo);
        return codigo;
    }

    private static String validacaoUUID(String uuidRandom) {
        String codigo = "";
        boolean codigoExistente = true;
        while (codigoExistente) {
            codigo = uuidRandom.substring(0,20);
            codigoExistente = codigosGerados.contains(codigo);
        }
        return codigo;
    }

    private static String gerarUUID() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom;
    }
}
