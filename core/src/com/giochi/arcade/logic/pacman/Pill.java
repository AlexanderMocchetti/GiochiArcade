package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pill {
    private boolean big = false;
    private Rectangle rect;
    private Vector2 position;
    private Texture img;
    public Pill(Rectangle rect, Texture img){
        this.rect = rect;
        position = new Vector2();
        rect.getCenter(position);
        this.img = img;
    }
    public void draw(Batch batch){
        batch.draw(img, rect.x, rect.y, rect.width, rect.height);
    }
    public void draw(ShapeRenderer shape){
        shape.setColor(Color.YELLOW);
        shape.rect(rect.x, rect.y, rect.width, rect.height);
    }
}
