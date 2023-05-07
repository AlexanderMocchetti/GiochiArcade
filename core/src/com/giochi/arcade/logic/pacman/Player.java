package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Player {
    private float x, y;
    private final float speed;
    private Direction direction = Direction.RIGHT;
    private final TiledMap map;
    public Player(float x, float y, float speed, TiledMap map){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.map = map;
    }
    public void update(float delta){
        float[] coordinates = advance();
        x = coordinates[0];
        y = coordinates[1];
        System.out.println("X: " + x + "\tY: " + y);
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