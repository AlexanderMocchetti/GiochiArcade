package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
import com.giochi.arcade.logic.pacman.Player;

public class LoadingScreen extends AbstractScreen{
    Sprite sprite;
    PacmanController pacmanController;
    Player player;
    OrthographicCamera cameraControl;
    public static final float WORLD_WIDTH = 15;
    public static final float WORLD_HEIGHT = 17.5f;
    public LoadingScreen(ArcadeGame parent) {
        super(parent);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        cameraControl = new OrthographicCamera();
        sprite = new Sprite(new Texture(Gdx.files.internal("graph.png")));
        player = new Player(0.5f,1.60f, 0.05f);
        pacmanController = new PacmanController(player);
    }

    @Override
    public void show() {
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
        player.update(delta);
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