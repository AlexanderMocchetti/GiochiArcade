package com.giochi.arcade.Pacman;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

public class Pills {
    public static final int PILL_NUMBER = 130;
    private final Map parent;
    private final Array<Pill> pillArray;
    private int pillsEaten;
    public Pills(Map parent) {
        this.parent = parent;
        pillsEaten = 0;
        pillArray = new Array<>(false, PILL_NUMBER);
    }
    public void addPill(Rectangle bounds, boolean big){
        pillArray.add(new Pill(bounds, big, parent.getPlayer()));
    }
    public void update(float delta){
        for (Pill pill : pillArray){
            pill.update(delta);
            if (!pill.isAlreadyChecked() && pill.isEaten()){
                if (pill.isBig())
                    parent.getGameManager().setPacmanInvincible(true);
                pillsEaten++;
            }
        }
    }
    public void draw(ShapeRenderer shape){
        for (Pill pill : pillArray){
            pill.draw(shape);
        }
    }

    public void reset(){
        pillsEaten = 0;
        for (Pill pill : pillArray){
            pill.reset();
        }
    }

    public int getPillsEaten() {
        return pillsEaten;
    }
    private static class Pill {
        private boolean visibile = true;
        private boolean eaten = false;
        private boolean alreadyChecked = false;
        private final boolean big;
        private final Rectangle bounds;
        private float timeSinceLastBlip = 0;
        private float radius;
        private final Vector2 position;
        private final Player player;
        public Pill(Rectangle bounds, boolean big, Player player){
            this.bounds = bounds;
            this.big = big;
            this.player = player;
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
        private boolean checkPlayerCollision() {
            return player.getBounds().contains(bounds);
        }
        public void draw(ShapeRenderer shape) {
            if(visibile)
                shape.circle(position.x, position.y, radius, 10);
        }
        public boolean isEaten() {
            if (!alreadyChecked && eaten)
                alreadyChecked = true;
            return eaten;
        }
        public void reset(){
            visibile = true;
            eaten = false;
            alreadyChecked = false;
        }

        public boolean isBig() {
            return big;
        }

        public boolean isAlreadyChecked() {
            return alreadyChecked;
        }
    }
}