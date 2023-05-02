package com.giochi.arcade.ui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.ArcadeGame;

abstract class AbstractScreen implements Screen {
    Camera camera;
    Viewport viewport;
    Batch batch;
    ShapeRenderer shape;
    final ArcadeGame parent;
    public static float WORLD_WIDTH = 1600;
    public static float WORLD_HEIGHT = 1000;

    public AbstractScreen(ArcadeGame parent){
        this.parent = parent;
        batch = parent.getBatch();
        shape = parent.getShape();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    }

    @Override
    public void show() {
        viewport.apply(true);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        shape.setProjectionMatrix(camera.combined);
    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void dispose(){

    }
}
