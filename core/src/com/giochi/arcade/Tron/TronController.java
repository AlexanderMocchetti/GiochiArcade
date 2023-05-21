package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class TronController{

    private TronPlayer tronPlayer1;
    private TronPlayer tronPlayer2;

    public TronController(TronPlayer tronPlayer1, TronPlayer tronPlayer2){
        this.tronPlayer1 = tronPlayer1;
        this.tronPlayer2 = tronPlayer2;
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            tronPlayer1.setDirection(0, 1);
            tronPlayer1.setRotation(90);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            tronPlayer1.setDirection(0, -1);
            tronPlayer1.setRotation(270);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            tronPlayer1.setDirection(-1, 0);
            tronPlayer1.setRotation(180);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            tronPlayer1.setDirection(1,0);
            tronPlayer1.setRotation(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            tronPlayer2.setDirection(0, 1);
            tronPlayer2.setRotation(270);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            tronPlayer2.setDirection(0, -1);
            tronPlayer2.setRotation(90);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            tronPlayer2.setDirection(-1, 0);
            tronPlayer2.setRotation(0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            tronPlayer2.setDirection(1, 0);
            tronPlayer2.setRotation(180);
        }
    }
}
