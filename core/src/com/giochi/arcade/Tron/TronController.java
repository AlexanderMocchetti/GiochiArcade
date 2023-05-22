package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class TronController{

    private TronPlayer player1;
    private TronPlayer player2;

    public TronController(TronPlayer player1, TronPlayer player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public void handleInput(boolean gameOver){
        if(gameOver){
            return;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.setDirection(0, 1);
            player1.setRotation(90);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player1.setDirection(0, -1);
            player1.setRotation(270);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player1.setDirection(-1, 0);
            player1.setRotation(180);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player1.setDirection(1,0);
            player1.setRotation(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player2.setDirection(0, 1);
            player2.setRotation(270);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player2.setDirection(0, -1);
            player2.setRotation(90);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player2.setDirection(-1, 0);
            player2.setRotation(0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player2.setDirection(1, 0);
            player2.setRotation(180);
        }
    }
}
