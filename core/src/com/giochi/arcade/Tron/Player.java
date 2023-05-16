package com.giochi.arcade.Tron;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture img;
    private Vector2 position;
    private Vector2 direction;
    private float speed;

    public Player(Texture img, Vector2 position, Vector2 direction, float speed) {
        this.img = img;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
    }

    public void move(){
        position.x += direction.x * speed;
        position.y += direction.y * speed;
    }

    public Texture getTexture() {
        return img;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setDirection(float x, float y) {
        this.direction = direction;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
