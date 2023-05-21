package com.giochi.arcade.Pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    private float x, y;
    private final float speed;
    private Direction direction = Direction.RIGHT;
    private final Texture img = new Texture(Gdx.files.internal("frog.png"));
    public Player(float x, float y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    public void update(float delta){
        float[] coordinates = advance();
        x = coordinates[0];
        y = coordinates[1];
        System.out.println("X: " + x + "\tY: " + y);
    }
    public void draw(Batch batch){
        batch.draw(img, x, y, 1, 1);
    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, 0.02f, 100);
    }
    private float[] advance(){
        float[] returnCoordinates = {x, y};
        switch(direction){
            case RIGHT:
                returnCoordinates[0] += speed;
                break;
            case LEFT:
                returnCoordinates[0] -= speed;
                break;
            case UP:
                returnCoordinates[1] += speed;
                break;
            case DOWN:
                returnCoordinates[1] -= speed;
                break;
        }
        return returnCoordinates;
    }

    public void setDirection(Direction direction) {
        if(direction != null)
            this.direction = direction;
    }
}