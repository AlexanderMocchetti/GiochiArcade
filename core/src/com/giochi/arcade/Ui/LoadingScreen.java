package com.giochi.arcade.Ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.Controller.PacmanController;
import com.giochi.arcade.Pacman.Player;

public class LoadingScreen extends AbstractScreen{
    Sprite sprite;
    PacmanController pacmanController;
    Player player;
    OrthographicCamera cameraControl;
    public static final float WORLD_WIDTH = 15;
    public static final float WORLD_HEIGHT = 15;
    public LoadingScreen(ArcadeGame parent) {
        super(parent);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        cameraControl = new OrthographicCamera();
        sprite = new Sprite(new Texture(Gdx.files.internal("graph.png")));
        player = new Player(1, 1, 0.001f);
        pacmanController = new PacmanController(player);
    }

    @Override
    public void show() {
        sprite.setPosition(0, 0);
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        player.update(delta);
        batch.end();
        shape.setColor(Color.BLACK);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        player.draw(shape);
        shape.end();
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