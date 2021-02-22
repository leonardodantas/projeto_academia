package com.projeto.academia.Projeto.Academia;

import java.util.Calendar;
import java.util.Date;

public class main {

    public static void main(String[] args) {
        Calendar teste = Calendar.getInstance();
        teste.setTime(new Date());
        System.out.println(teste.get(Calendar.MONTH));

    }
}
