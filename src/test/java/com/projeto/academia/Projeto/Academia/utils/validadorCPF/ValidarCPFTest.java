package com.projeto.academia.Projeto.Academia.utils.validadorCPF;

import com.projeto.academia.Projeto.Academia.utils.cpf.ValidarCPF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ValidarCPFTest {

    @InjectMocks
    private ValidarCPF validarCPF;

    @Test
    public void deveFormatarCPF(){
        String cpf = "44455566678";

        String cpfFormatado = validarCPF.formatarCPF(cpf);

        assertEquals(cpfFormatado,"444.555.666-78");
    }

    @Test(expected = Exception.class)
    public void deveLancarExececaoPeloTamanhoDoCPF(){
        validarCPF.formatarCPF("123456789123");
    }

    @Test(expected = Exception.class)
    public void deveLancarExececaoPeloFormatoDoCPF(){
        validarCPF.formatarCPF("7.841.123.4");
    }
}
