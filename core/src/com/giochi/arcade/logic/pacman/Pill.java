package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pill {
    private boolean visibile = true;
    private boolean eaten = false;
    private final boolean big;
    private final Rectangle rect;
    private float timeSinceLastBlip = 0;
    private float radius;
    private final Vector2 position;
    public Pill(Rectangle rect, boolean big){
        this.rect = rect;
        this.big = big;
        radius = rect.width;
        if(big)
            radius *= GameManager.scalePillBig;
        else
            radius *= GameManager.scalePill;
        position = new Vector2();
        rect.getCenter(position);
    }
    public Pill(Rectangle rect){
        this(rect, false);
    }
    public void update(float delta, Player player){
        if(eaten)
            return;
        if(checkPlayerCollision(player)){
            visibile = false;
            eaten = true;
            return;
        }
        if(big)
            checkTimeSinceLastBlip(delta);
    }
    private void checkTimeSinceLastBlip(float delta){
        timeSinceLastBlip += delta;
        if(timeSinceLastBlip >= GameManager.pillIntermittentTime){
            timeSinceLastBlip = 0;
            visibile = !visibile;
        }
    }
    private boolean checkPlayerCollision(Player player){
       return player.getRectangle().contains(rect);
    }
    public void draw(ShapeRenderer shape) {
       if(visibile)
           shape.circle(position.x, position.y, radius, 10);
    }
}
