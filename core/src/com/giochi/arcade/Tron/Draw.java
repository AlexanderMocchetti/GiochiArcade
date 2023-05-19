package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Draw {

    private SpriteBatch batch;
    private ArrayList<Laser> lasers;
    private Player player1;
    private Player player2;

    public Draw(SpriteBatch batch, ArrayList<Laser> lasers, Player player1, Player player2){
        this.batch = batch;
        this.lasers = lasers;
        this.player1 = player1;
        this.player2 = player2;
    }


}
