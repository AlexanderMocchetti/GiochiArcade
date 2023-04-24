package com.giochi.arcade.ui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.ArcadeGame;

abstract class AbstractScreen implements Screen {
    final Camera camera;
    final Viewport viewport;
    final Batch batch;
    final ArcadeGame parent;
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 1000;

    /**
     * @param parent Parent object
     * @see ArcadeGame
     */
    public AbstractScreen(ArcadeGame parent){
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        this.parent = parent;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void dispose(){
        batch.dispose();
    }

}
