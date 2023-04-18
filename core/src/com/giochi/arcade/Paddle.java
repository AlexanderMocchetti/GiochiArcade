package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int width, height, x, y;

    public Paddle(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void update(){
        int cursorXPosition = Gdx.input.getX();
        if(cursorXPosition > 0 && cursorXPosition + width < Gdx.graphics.getWidth())
            x = cursorXPosition;
    }
    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width, height);
    }
}
