package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player {
    private final Vector2 positionVector, targetPositionVector;
    private Vector2 speedVector, targetSpeedVector;
    private final Sprite sprite;
    private final float width, height, speed;
    public Player(float x, float y, float width, float height, float speed){
        positionVector = new Vector2(x, y);
        targetPositionVector = new Vector2(positionVector);
        targetSpeedVector = new Vector2(speed, 0);
        this.width = width;
        this.height = height;
        this.speed = speed;
        sprite = new Sprite(new Texture("pac_man_0.png"));
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
    }
    public void update(float delta){
        handleAutomatedTurn(delta);
        advance(delta);
        System.out.println("X: " + positionVector.x + "\tY: " + positionVector.y + "\tCentered: " + checkCenteredInTile());
        if(checkWallCollision(targetPositionVector.x, targetPositionVector.y, width, height))
            return;
        positionVector.set(targetPositionVector);
        sprite.setPosition(positionVector.x, positionVector.y);
    }
    public void draw(Batch batch){
        sprite.draw(batch);
    }
    private boolean checkWallCollision(float x, float y, float width, float height){
        return checkWallCollision(new Rectangle(x, y, width, height));
    }
    private boolean checkWallCollision(Rectangle rectangle){
        ArrayList<Rectangle> walls = GameManager.instance.getWalls();
        for(Rectangle wall: walls){
            if(rectangle.overlaps(wall))
                return true;
        }
        return false;
    }
    private boolean checkCenteredInTile(){
        float xCenter, yCenter, xCenterTile, yCenterTile;
        xCenter = positionVector.x + width/2;
        yCenter = positionVector.y + height/2;
        xCenterTile = (float) Math.floor(xCenter) + 0.5f;
        yCenterTile = (float) Math.floor(yCenter) + 0.5f;
        return Math.abs(xCenter - xCenterTile) < GameManager.centerTileError &&
                Math.abs(yCenter - yCenterTile) < GameManager.centerTileError;
    }
    private void handleAutomatedTurn(float delta){
        float x, y;
        x = positionVector.x + targetSpeedVector.x * delta;
        y = positionVector.y + targetSpeedVector.y * delta;
        if(!checkWallCollision(x, y, width, height) && checkCenteredInTile())
            speedVector = targetSpeedVector;
    }
    private void advance(float delta){
        targetPositionVector.set(positionVector);
        targetPositionVector.add(speedVector.x * delta, speedVector.y * delta);
    }
    public float getSpeed() {
        return speed;
    }
    public void setTargetSpeedVector(Vector2 targetSpeedVector) {
        this.targetSpeedVector = targetSpeedVector;
    }
    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }
}