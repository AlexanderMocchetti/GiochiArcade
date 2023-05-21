package com.giochi.arcade.Pong.pong;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    private float x;
    private float y;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    private float width;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    private float height;
    private float speed;
    private final int p;
    public Player(float x, float y, float width, float height, float speed,int p) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.p=p;

    }
    public void update(){
        if(y + height/2 >= PongScreen.WORLD_HEIGHT )
            y=0+height;
        if( y - height/2 <= 0)
            y=PongScreen.WORLD_HEIGHT-height;
        if(p==1) {
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                y -= speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                y += speed;
            }
        }
        if(p==2) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                y -= speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                y += speed;
            }
        }
    }
    public void draw(ShapeRenderer shape){
        shape.rect(x,y,width,height);
    }
}
