package com.projeto.academia.Projeto.Academia.utils.imc;

import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;

import java.text.DecimalFormat;

public class CalculatorIMC {

    public static double calculator(RatingDTO rating){
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        double heightSquare = rating.getHeight() * rating.getHeight();
        double imcCalculated = rating.getWeight() / heightSquare;
        return Double.parseDouble(decimalFormat.format(imcCalculated).replace(",", "."));
    }

}
