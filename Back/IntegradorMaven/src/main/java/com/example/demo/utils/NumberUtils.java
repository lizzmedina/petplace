package com.example.demo.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtils {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    private NumberUtils(){

    }

    public static Double roundTwoDecimals(Double value){
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(value).replace(",", "."));
    }
}
