package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class TronController{

    private Player player1;
    private Player player2;

    public TronController(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public void update(){
        handleInput();
        player1.move();
        player2.move();
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player1.setDirection(0, 1);
            player1.setRotation(90);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player1.setDirection(0, -1);
            player1.setRotation(270);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player1.setDirection(-1, 0);
            player1.setRotation(180);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player1.setDirection(1,0);
            player1.setRotation(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player2.setDirection(0, 1);
            player2.setRotation(90);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player2.setDirection(0, -1);
            player2.setRotation(270);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player2.setDirection(-1, 0);
            player2.setRotation(180);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player2.setDirection(1, 0);
            player2.setRotation(0);
        }
    }
}
