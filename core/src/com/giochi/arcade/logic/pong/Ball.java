package com.giochi.arcade.logic.pong;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ui.PongScreen;

public class Ball {
    private float x, y, xSpeed, ySpeed,xBase,yBase;
    private final float radius;
    private int p1,p2;

    private String s1 , s2;
    public Ball(float x, float y, float radius, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        xBase=x;
        yBase=y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        p1=0;
        p2=0;
    }
    public void update(float delta){
        if(y + radius > PongScreen.WORLD_HEIGHT || y - radius < 0)
            ySpeed *= -1;
        if(x + radius > PongScreen.WORLD_WIDTH || x - radius < 0)
            xSpeed *= -1;
        x += xSpeed;
        y += ySpeed;
        if(x<(20-radius*2)) {
            x=xBase;
            y=yBase;
            p2++;
            s2=Integer.toString(p2);
        }
        if( x>PongScreen.WORLD_WIDTH-(20-radius*2)){
            x=xBase;
            y=yBase;
            p1++;
            s1=Integer.toString(p1);
        }
    }


    public void draw(ShapeRenderer shape){
        shape.circle(x, y, radius);
       // shape.rect(PongScreen.WORLD_WIDTH/2,PongScreen.WORLD_HEIGHT,10,10);

    }
}