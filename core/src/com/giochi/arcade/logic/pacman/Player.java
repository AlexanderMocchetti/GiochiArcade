package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player{
    private final Vector2 positionVector, targetPositionVector;
    private Vector2 speedVector, targetSpeedVector;
    private final Sprite sprite;
    private final float width, height, speed;
    private final Animation<TextureRegion> animation;
    private TextureRegion currentKeyFrame;
    private float
            animationStateTime = 0,
            rotationDegrees = 0;
    public Player(float x, float y, float width, float height, float speed){
        positionVector = new Vector2(x, y);
        targetPositionVector = new Vector2(positionVector);
        targetSpeedVector = new Vector2(speed, 0);
        this.width = width;
        this.height = height;
        this.speed = speed;
        TextureAtlas atlas = GameManager.instance.getAtlas();
        animation = new Animation<TextureRegion>(
                GameManager.playerAnimationTimeFrame,
                atlas.findRegions("pac_man"),
                Animation.PlayMode.LOOP);
        currentKeyFrame = atlas.findRegion("pac_man", 0);
        sprite = new Sprite(currentKeyFrame);
        sprite.setOrigin(width / 2, height / 2);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
    }
    public void update(float delta){
        handleAssistedTurn(delta);
        advance(delta);
        System.out.println(
                "X: " + positionVector.x +
                "\tY: " + positionVector.y +
                        "\tCentered: " + checkCenteredInTile() +
                        "\tDelta: " + delta
        );
        if(checkWallCollision(targetPositionVector.x, targetPositionVector.y, width, height))
            return;
        positionVector.set(targetPositionVector);
        handleAnimation(delta);
        sprite.setRegion(currentKeyFrame);
        sprite.setPosition(positionVector.x, positionVector.y);
    }
    private void handleAnimation(float delta) {
        animationStateTime += delta;
        currentKeyFrame = animation.getKeyFrame(animationStateTime);
    }
    public void draw(Batch batch){
        sprite.draw(batch);
    }
    private boolean checkWallCollision(float x, float y, float width, float height){
        return checkWallCollision(new Rectangle(x, y, width, height));
    }
    private boolean checkWallCollision(Rectangle rectangle){
        Array<Rectangle> walls = GameManager.instance.getWalls();
        rectangle.x = PacmanUtils.roundFloatToNthDigit(rectangle.x, 3);
        rectangle.y = PacmanUtils.roundFloatToNthDigit(rectangle.y, 3);
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
    // TODO: Figure out tf is going on with player random blockings
    private void handleAssistedTurn(float delta){
        float x, y;
        x = positionVector.x + targetSpeedVector.x * delta;
        y = positionVector.y + targetSpeedVector.y * delta;
        if(!checkWallCollision(x, y, width, height) && checkCenteredInTile()) {
            speedVector = targetSpeedVector;
            sprite.setRotation(rotationDegrees);
        }
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
    public void setRotationDegrees(float rotationDegrees){
        this.rotationDegrees = rotationDegrees;
    }
}