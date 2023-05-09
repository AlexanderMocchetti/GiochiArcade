package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player {
    private float x, y;
    private Sprite sprite;
    private final float speed;
    private Direction direction = Direction.RIGHT;
    public Player(float x, float y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        sprite = new Sprite(new Texture("pac_man_0.png"));
        sprite.setPosition(x, y);
        sprite.setSize(0.9f,0.9f);
    }
    public void update(float delta){
        float[] coordinates = advance();
        System.out.println("X: " + x + "\tY: " + y);
        if(!checkWallCollision(new Rectangle(coordinates[0], coordinates[1], sprite.getWidth(), sprite.getHeight())))
            return;
        x = coordinates[0];
        y = coordinates[1];
        sprite.setPosition(x,y);
    }
    public void draw(Batch batch){
        sprite.draw(batch);
    }
    private boolean checkCornerCloseness(){
        float coordinate = Float.MAX_VALUE;
        switch (direction) {
            case UP:
            case DOWN:
                coordinate = y;
                break;
            case RIGHT:
            case LEFT:
                coordinate = x;
                break;
        }
        return Math.abs(coordinate - Math.floor(coordinate)) < GameManager.cornerTightness || Math.abs(coordinate - Math.ceil(coordinate)) < GameManager.cornerTightness;
    }
    private boolean checkWallCollision(Rectangle rectangle){
        ArrayList<Rectangle> walls = GameManager.instance.getWalls();
        for(Rectangle wall: walls){
            if(rectangle.overlaps(wall))
                return false;
        }
        return true;
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
    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }
}