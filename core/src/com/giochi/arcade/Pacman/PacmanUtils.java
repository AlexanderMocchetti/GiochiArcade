package com.giochi.arcade.logic.pacman;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

final class PacmanUtils {
    private PacmanUtils() {  }
    public static float roundFloatToNthDigit(float value, int digit){
        BigDecimal bg = (new BigDecimal(String.valueOf(value))).setScale(digit, RoundingMode.UP);
        return bg.floatValue();
    }
    public static String matrix2dToString(int[][] matrix){
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append("{\n");
        for (int[] ints : matrix) {
            stringBuilder.append(Arrays.toString(ints));
            stringBuilder.append('\n');
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
