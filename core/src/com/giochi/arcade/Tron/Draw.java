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

    public void render(float delta){
        // Disegna lo sfondo
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Disegna la scia delle moto
        batch.begin();
        for(Laser laser : lasers) {
            laser.draw(batch);
        }
        batch.end();

        // Disegna i giocatori
        batch.begin();
        batch.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
        batch.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
        batch.end();
    }
}
