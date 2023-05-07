package com.giochi.arcade.logic.pong;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ui.PongScreen;
public class Player {
    private float x, y, width, height, speed;
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
        if(p==1) {

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += speed;
            }
        }
        if(p==2) {
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                x -= speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                x += speed;
            }
        }
    }
    public void draw(ShapeRenderer shape){
        shape.rect(x,y,width,height);
    }
}
