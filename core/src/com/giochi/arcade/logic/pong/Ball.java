package com.giochi.arcade.logic.pong;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ui.PongScreen;

public class Ball {
    private float x, y, xSpeed, ySpeed;
    private final float radius;
    public Ball(float x, float y, float radius, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void update(float delta){
        if(y + radius> 200 || y - radius < 0)
            ySpeed *= -1;
        if(x + radius > 200 || x - radius < 0)
            xSpeed *= -1;
        x += xSpeed;
        y += ySpeed;
    }
    public void draw(ShapeRenderer shape){
        shape.circle(x, y, radius);
    }
}