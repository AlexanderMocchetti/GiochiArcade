package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
import com.giochi.arcade.logic.pacman.Player;

public class LoadingScreen extends AbstractScreen{
    Sprite sprite;
    PacmanController pacmanController;
    Player player;
    OrthographicCamera cameraControl;
    public LoadingScreen(ArcadeGame parent) {
        super(parent);
        WORLD_WIDTH = 15;
        WORLD_HEIGHT = 17.5f;
        cameraControl = new OrthographicCamera();
        viewport.setWorldSize(WORLD_WIDTH, WORLD_HEIGHT);
        sprite = new Sprite(new Texture(Gdx.files.internal("graph.png")));
        player = new Player(0.5f,1.60f, 0.05f);
        pacmanController = new PacmanController(player);
    }

    @Override
    public void show() {
        super.show();
        sprite.setPosition(0, 1);
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT-2);
        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        player.update();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        sprite.getTexture().dispose();
    }
}