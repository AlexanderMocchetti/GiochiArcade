package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x, y, size, xSpeed, ySpeed;
    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void update(){
        x += xSpeed;
        y += ySpeed;

        if(x - size < 0 || x + size > Gdx.graphics.getWidth())
            xSpeed *= -1;

        if(y - size < 0 || y + size > Gdx.graphics.getHeight())
            ySpeed *= -1;
    }
    public void draw(ShapeRenderer shape){
        shape.circle(x, y, size);
    }

    public boolean collidesWith(Paddle paddle){
        return true;
    }
}
