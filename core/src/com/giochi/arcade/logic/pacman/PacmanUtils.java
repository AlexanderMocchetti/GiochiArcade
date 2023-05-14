package com.giochi.arcade.logic.pacman;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class PacmanUtils {
    private PacmanUtils() {  }
    public static float roundFloatToNthDigit(float value, int digit){
        BigDecimal bg = (new BigDecimal(String.valueOf(value))).setScale(digit, RoundingMode.UP);
        return bg.floatValue();
    }
}
