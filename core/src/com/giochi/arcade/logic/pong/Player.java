package com.giochi.arcade.logic.pong;

public class Player {
    private float x, y, width, height, speed;
    private final Axis movableAxis;
    public Player(float x, float y, float width, float height, Axis movableAxis, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.movableAxis = movableAxis;
        this.speed = speed;
    }
    public void update(float delta){

    }
    public void setCoordinates(float value){

    }
    public Axis getMovableAxis() {
        return movableAxis;
    }
}
