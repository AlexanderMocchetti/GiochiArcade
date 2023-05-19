package com.giochi.arcade.Tron;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Sprite sprite;
    private Vector2 position;
    private Vector2 direction;
    private float speed;
    private float rotation = 0;
    private Laser laser;

    public Player(Texture img, Vector2 position, Vector2 direction, float speed, Texture Laser) {
        sprite = new Sprite(img);
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.laser = laser;
    }

    public void move(){
        position.x += direction.x * speed;
        position.y += direction.y * speed;
        sprite.setPosition(position.x, position.y);
        sprite.setRotation(rotation);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setImg(Texture img) {
        sprite.setTexture(img);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setDirection(float x, float y) {
        if(direction.x != -x || direction.y != -y){
            direction.set(x, y);
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setRotation(float rotation) {
        if(Math.abs(this.rotation - rotation)!= 180){           //Math.abs valore assoluto != 180 (direziona opposta) set nuova rotazione
            this.rotation = rotation;
        }
    }
}
