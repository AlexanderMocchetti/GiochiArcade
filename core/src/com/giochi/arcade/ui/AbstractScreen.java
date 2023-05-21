package com.giochi.arcade.ui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.ArcadeGame;

abstract class AbstractScreen implements Screen {
    final Batch batch;
    final ShapeRenderer shape;
    final ArcadeGame parent;
    Camera camera;
    Viewport viewport;

    public AbstractScreen(ArcadeGame parent) {
        this.parent = parent;
        batch = parent.getBatch();
        shape = parent.getShape();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}