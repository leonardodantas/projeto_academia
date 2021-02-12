package com.projeto.academia.Projeto.Academia.utils.geradorID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class GeradorID {

    private static GeradorID geradorID;
    private List<String> codigosGerados;

    private GeradorID(){
        this.codigosGerados = new ArrayList<>();
    }

    public static GeradorID getInstance(){
        if (Objects.isNull(geradorID)){
            return new GeradorID();
        }
        return geradorID;

    }
    public String gerarCodigo(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        String codigo = "";
        boolean codigoExistente = true;
        while (codigoExistente) {
            codigo = myRandom.substring(0,20);
            codigoExistente = codigosGerados.contains(codigo);
        }
        codigosGerados.add(codigo);
        return codigo;
    }
}
