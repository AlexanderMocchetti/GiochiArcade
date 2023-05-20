package com.giochi.arcade.logic.pong;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ui.PongScreen;

public class Ball {
    private float x, y, xSpeed, ySpeed,xBase,yBase;
    private final float radius;
    private Player p1,p2;

    public Ball(float x, float y, float radius, float xSpeed, float ySpeed,Player p1, Player p2) {
        this.x = x;
        this.y = y;
        xBase=x;
        yBase=y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.p1=p1;
        this.p2=p2;

    }
    public void update(float delta){
        if(y + radius > PongScreen.WORLD_HEIGHT || y - radius < 0)
            ySpeed *= -1;
        if(x + radius == p1.getX() || x + radius ==p2.getX())
            xSpeed *= -1;
        x += xSpeed;
        y += ySpeed;
        if(x<(p1.getX()-radius*2)|| x>p2.getX()-radius*2) {
            x=xBase;
            y=yBase;

        }

    }


    public void draw(ShapeRenderer shape){
        shape.circle(x, y, radius);


    }
}