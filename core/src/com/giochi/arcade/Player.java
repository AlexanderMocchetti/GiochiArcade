package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Sprite sprite;
    public Player(Texture img){
        sprite = new Sprite(img);
        position = new Vector2(Gdx.graphics.getHeight() / 2, 0);
        sprite.setScale(0.5F);
        sprite.setColor(Color.GREEN);
    }
    public void draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
