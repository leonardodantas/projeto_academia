package com.projeto.academia.Projeto.Academia.utils.geradorID;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GeradorIDTest {

    @InjectMocks
    private GeradorID geradorID;

    @Test
    public void deveGerarStringAleatorioDeTamanho20(){

        String codigo = geradorID.gerarCodigo();
        Assertions.assertEquals(codigo.length(), 20);
    }
}
