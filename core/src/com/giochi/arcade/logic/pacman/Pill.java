package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pill {
    private boolean visibile = true;
    private boolean eaten = false;
    private boolean alreadyChecked = false;
    private final boolean big;
    private final Rectangle bounds;
    private float timeSinceLastBlip = 0;
    private float radius;
    private final Vector2 position;

    private Player player;
    public Pill(Rectangle bounds, boolean big){
        this.bounds = bounds;
        this.big = big;
        radius = bounds.width;
        if(big)
            radius *= GameManager.scalePillBig;
        else
            radius *= GameManager.scalePill;
        position = new Vector2();
        bounds.getCenter(position);
    }
    public void update(float delta){
        if(player == null)
            throw new AssertionError("Assign Pill object to a Player object");
        if(eaten)
            return;
        if(checkPlayerCollision()){
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
    private boolean checkPlayerCollision(){
        return player.getBounds().contains(bounds);
    }
    public void draw(ShapeRenderer shape) {
       if(visibile)
           shape.circle(position.x, position.y, radius, 10);
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isEaten() {
        if (alreadyChecked)
            return false;
        if (eaten)
            alreadyChecked = true;
        return eaten;
    }
}
