package com.giochi.arcade.Pacman;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.giochi.arcade.logic.pacman.ai.Node;

public class Player{
    private final Map map;
    private final Vector2 spawnPositionVector;
    private final Vector2 positionVector, targetPositionVector;
    private Vector2 speedVector, targetSpeedVector;
    private final Sprite sprite;
    private final float width, height, speed;
    private final Animation<TextureRegion> animation;
    private TextureRegion currentKeyFrame;
    private float
            animationStateTime = 0,
            rotationDegrees = 0;
    public Player(float x, float y, float width, float height, float speed, Map map){
        positionVector = new Vector2(x, y);
        spawnPositionVector = new Vector2(x, y);
        targetPositionVector = new Vector2(positionVector);
        targetSpeedVector = new Vector2(speed, 0);
        speedVector = targetSpeedVector;
        this.map = map;
        this.width = width;
        this.height = height;
        this.speed = speed;
        TextureAtlas atlas = map.getPlayerAtlas();
        animation = new Animation<TextureRegion>(
                GameManager.playerAnimationTimeFrame,
                atlas.findRegions("pac_man"),
                Animation.PlayMode.LOOP);
        currentKeyFrame = atlas.findRegion("pac_man", 0);
        sprite = new Sprite(currentKeyFrame);
        sprite.setOrigin(width / 2, height / 2);
        sprite.setBounds(x, y, width, height);
    }
    public Node getGridLocation(){
        return PacmanUtils.getGridLocation(sprite.getBoundingRectangle(), map.getGraph());
    }
    public void update(float delta){
        handleAssistedTurn(delta);
        advance(delta);
        Rectangle rectangle = new Rectangle(targetPositionVector.x, targetPositionVector.y, width, height);
        System.out.println("Bounds: " + sprite.getBoundingRectangle() + "Target speed: " + targetSpeedVector + "\tSpeed: " + speedVector);
        if(
                PacmanUtils.checkSingleCollision(rectangle, map.getGate()) ||
                PacmanUtils.checkMultipleCollision(rectangle, map.getWallBounds()))
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
    private boolean checkCenteredInTile(){
        return PacmanUtils.checkCenteredInTile(sprite.getBoundingRectangle());
    }
    private void handleAssistedTurn(float delta){
        float x, y;
        x = positionVector.x + targetSpeedVector.x * delta;
        y = positionVector.y + targetSpeedVector.y * delta;
        Rectangle rectangle = new Rectangle(x, y, width, height);
        if(
                !PacmanUtils.checkMultipleCollision(rectangle, map.getWallBounds())
                && checkCenteredInTile()
                && !PacmanUtils.checkSingleCollision(rectangle, map.getGate())
                ) {
            speedVector = targetSpeedVector;
            sprite.setRotation(rotationDegrees);
        }
    }
    private void advance(float delta){
        targetPositionVector.set(positionVector);
        targetPositionVector.add(speedVector.x * delta, speedVector.y * delta);
    }
    public void reset(){
        positionVector.set(spawnPositionVector);
        targetPositionVector.set(positionVector);
        targetSpeedVector = new Vector2(speed, 0);
        speedVector = targetSpeedVector;
        sprite.setPosition(positionVector.x, positionVector.y);
        animationStateTime = 0;
        rotationDegrees = 0;
    }
    public float getSpeed() {
        return speed;
    }
    public void setTargetSpeedVector(Vector2 targetSpeedVector) {
        this.targetSpeedVector = targetSpeedVector;
    }
    public void setRotationDegrees(float rotationDegrees){
        this.rotationDegrees = rotationDegrees;
    }
    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }
}