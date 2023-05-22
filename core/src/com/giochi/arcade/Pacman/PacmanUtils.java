package com.giochi.arcade.Pacman;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.giochi.arcade.Pacman.ai.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public final class PacmanUtils {
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
    public static boolean checkCenteredInTile(Rectangle rectangle){
        float xCenter, yCenter, xCenterTile, yCenterTile;
        xCenter = rectangle.x + rectangle.width/2;
        yCenter = rectangle.y + rectangle.height/2;
        xCenterTile = (float) Math.floor(xCenter) + 0.5f;
        yCenterTile = (float) Math.floor(yCenter) + 0.5f;
        return Math.abs(xCenter - xCenterTile) < GameManager.centerTileError &&
                Math.abs(yCenter - yCenterTile) < GameManager.centerTileError;
    }
    public static boolean checkMultipleCollision(float x, float y, float width, float height, Array<Rectangle> rectangles){
        return checkMultipleCollision(new Rectangle(x, y, width, height), rectangles);
    }
    public static boolean checkMultipleCollision(Rectangle rectangle, Array<Rectangle> rectangles){
        rectangle.x = roundFloatToNthDigit(rectangle.x, 3);
        rectangle.y = roundFloatToNthDigit(rectangle.y, 3);
        for(Rectangle rect: rectangles){
            if(rectangle.overlaps(rect))
                return true;
        }
        return false;
    }
    public static boolean checkSingleCollision(Rectangle rectangle, Rectangle rectangle1){
        rectangle.x = roundFloatToNthDigit(rectangle.x, 3);
        rectangle.y = roundFloatToNthDigit(rectangle.y, 3);
        return rectangle.overlaps(rectangle1);
    }
    public static Node getGridLocation(Rectangle rectangle, Graph graph){
        int xTile, yTile;
        xTile = (int) Math.floor(rectangle.x + rectangle.width / 2);
        yTile = (int) Math.floor(rectangle.y + rectangle.height / 2);
        return graph.getNode(xTile, yTile);
    }
}
