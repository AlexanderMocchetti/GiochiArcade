package com.giochi.arcade.SpaceInvaders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceInvadersAlien
{

    private Vector2 positionAlien;

    private Vector2 initialPosition;

    private Sprite spriteAlien;

    private boolean alive = true;

    public SpaceInvadersAlien(Vector2 positionAlien, Texture imgAlien, Color color)
    {
        this.positionAlien = positionAlien;
        initialPosition = positionAlien;
        spriteAlien = new Sprite(imgAlien);
        spriteAlien.setSize(20 , 20);
        spriteAlien.setColor(color);
        spriteAlien.setScale(1);
    }

    public void draw(SpriteBatch batchAlien)
    {
        spriteAlien.setPosition(positionAlien.x, positionAlien.y);
        spriteAlien.draw(batchAlien);
    }

    public Sprite getSpriteAlien() {
        return spriteAlien;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Vector2 getInitialPosition() {
        return initialPosition;
    }


    public Vector2 getPositionAlien() {
        return positionAlien;
    }

    public void setPositionAlien(Vector2 positionAlien) {
        this.positionAlien = positionAlien;
    }
}
