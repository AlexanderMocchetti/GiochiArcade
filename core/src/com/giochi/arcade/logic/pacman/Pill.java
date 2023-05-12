package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pill {
    private boolean big;
    private boolean visibile = true;
    private Rectangle rect;
    private float radius;
    private Vector2 position;
    public Pill(Rectangle rect, boolean big){
        this.rect = rect;
        this.big = big;
        radius = rect.width * GameManager.scalePill;
        position = new Vector2();
        rect.getCenter(position);
    }
    public Pill(Rectangle rect){
        this(rect, false);
    }
    public void update(Player player){
        if(checkPlayerCollision(player))
            visibile = false;
    }
    private boolean checkPlayerCollision(Player player){
       return player.getRectangle().contains(rect);
    }
    public void draw(ShapeRenderer shape) {
        if(!visibile)
            return;
        shape.setColor(Color.YELLOW);
        shape.circle(position.x, position.y, radius, 10);
    }
}
