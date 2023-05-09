package com.giochi.arcade.logic.pong;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ui.PongScreen;

public class Ball {
    private float x, y, xSpeed, ySpeed,xBase,yBase;
    private final float radius;
    public Ball(float x, float y, float radius, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        xBase=x;
        yBase=y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void update(float delta){
        if(y + radius > PongScreen.WORLD_HEIGHT || y - radius < 0)
            ySpeed *= -1;
        if(x + radius > PongScreen.WORLD_WIDTH || x - radius < 0)
            xSpeed *= -1;
        x += xSpeed;
        y += ySpeed;
        if(x<(20-radius) || x>PongScreen.WORLD_WIDTH-(20-radius)) {
            x = xBase;
            y=yBase;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.circle(x, y, radius);
    }
}