package com.giochi.arcade.ui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.ArcadeGame;

abstract class AbstractScreen implements Screen {
    Camera camera;
    Viewport viewport;
    Batch batch;
    ArcadeGame parent;
    public AbstractScreen(ArcadeGame parent){
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        this.parent = parent;
    }
    @Override
    public void resize(int width, int height) {

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
