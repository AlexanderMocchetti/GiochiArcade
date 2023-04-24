package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.giochi.arcade.ArcadeGame;

public class LoadingScreen extends AbstractScreen{
    Sprite sprite;

    public LoadingScreen(ArcadeGame parent) {
        super(parent);
    }

    @Override
    public void show() {
        viewport.apply(true);
        sprite = new Sprite(new Texture(Gdx.files.internal("cosi.jpeg")));
        sprite.setPosition(0, 0);
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
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